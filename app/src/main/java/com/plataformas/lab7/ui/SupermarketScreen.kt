package com.plataformas.lab7.ui

import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.plataformas.lab7.database.supermarket.SuperMarketItemEntity
import com.plataformas.lab7.viewmodel.SupermarketViewModel
import java.io.File

@Composable
fun SupermarketScreen(navController: NavController, viewModel: SupermarketViewModel) {
    val items by viewModel.items.collectAsState()
    val context = LocalContext.current
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    var imagePath by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadItems()
    }

    // Crear un ActivityResultLauncher para capturar imágenes
    val imageCaptureLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            imagePath?.let { viewModel.addItem(itemName, itemQuantity.toIntOrNull() ?: 1, it) }
            itemName = ""
            itemQuantity = ""
        }
    }

    fun launchCamera(context: Context) {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "${System.currentTimeMillis()}.jpg")
        imagePath = file.absolutePath
        val uri = FileProvider.getUriForFile(context, "com.plataformas.lab7.fileprovider", file)
        imageCaptureLauncher.launch(uri)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Input para el nombre del artículo
        TextField(
            value = itemName,
            placeholder = { Text("Nombre") },
            onValueChange = { itemName = it },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Input para la cantidad
        TextField(
            value = itemQuantity,
            placeholder = { Text("Cantidad") },
            onValueChange = { itemQuantity = it },
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { if (itemName.isNotBlank() && itemQuantity.isNotBlank()) viewModel.addItem(itemName, itemQuantity.toInt(), imagePath) }) {
                Text("Agregar Artículo")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { launchCamera(context) }) {
                Text("Tomar Foto")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items) { item ->
                SupermarketItemRow(item, viewModel)
            }
        }
    }
}

@Composable
fun SupermarketItemRow(item: SuperMarketItemEntity, viewModel: SupermarketViewModel) {
    var quantity by remember { mutableStateOf(item.quatity.toString()) }
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(


        ){
            Text(text = item.itemName, style = MaterialTheme.typography.bodyMedium)
            TextField(

                value = quantity,
                onValueChange = {
                    quantity = it
                    viewModel.updateItemQuantity(item.id, it.toIntOrNull() ?: item.quatity)
                },
                modifier = Modifier.width(180.dp),
                label = { Text("Cantidad") }
            )
        }

        // Mostrar imagen del artículo si está disponible
        if (item.imagePath != null) {
            Image(
                painter = rememberImagePainter(data = item.imagePath),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
        }

        IconButton(onClick = { viewModel.deleteItem(item.id) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
        }
    }
}