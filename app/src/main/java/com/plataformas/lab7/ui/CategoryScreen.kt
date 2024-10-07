package com.plataformas.lab7.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.plataformas.lab7.model.Category
import com.plataformas.lab7.viewmodel.CategoryViewModel


@Composable
fun CategoryScreen(navController: NavController) {
    // Obteniendo las categorías desde el ViewModel
    val viewModel: CategoryViewModel = viewModel()
    val categories = viewModel.categories.value

    // Mostrar la UI mientras las categorías se cargan o si hay un error
    when {
        categories.isEmpty() -> {
            println(categories)
            println("Cargando categorías...")
            AsyncImage(
                model = "https://img.freepik.com/vector-gratis/coche-sedan-rojo-estilo-dibujos-animados-aislado-sobre-fondo-blanco_1308-64900.jpg",
                contentDescription = "Image description",
                modifier = Modifier.size(100.dp) // Adjust size as needed
            )
            CircularProgressIndicator()  // Indicador de carga
        }
        else -> {
            println("Categorías cargadas")
            LazyColumn(contentPadding = PaddingValues(8.dp)) {
                item {
                    Text(
                        text = "Categorías",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                items(categories) { category ->
                    CategoryItem(category, navController)
                }
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category, navController: NavController) {
    // Aquí puedes personalizar cómo mostrar las categorías
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("recipe_screen/${category.name}")
            }.shadow(
            elevation = 4.dp,
            shape = MaterialTheme.shapes.medium,
            clip = true
        ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp)) {
            Image(
                painter = rememberImagePainter(data = category.imageUrl),
                contentDescription = category.description,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = category.name, style = MaterialTheme.typography.titleMedium)
                Text(text = category.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

