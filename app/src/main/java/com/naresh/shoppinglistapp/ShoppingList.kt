package com.naresh.shoppinglistapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

data class ShoppingItem(
    val id:Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false
)

@Composable
fun ShoppingListApp() {
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = "Shopping List",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 22.dp)
        )
        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Add Item", modifier = Modifier.padding(8.dp), style = MaterialTheme.typography.titleMedium)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(sItems) { item ->
                if (item.isEditing) {
                    ShoppingItemEditor(item = item, onEditComplete = { editedName, editedQuantity ->
                        sItems = sItems.map { it.copy(isEditing = false) }
                        val editedItem = sItems.find { it.id == item.id }
                        editedItem?.let {
                            it.name = editedName
                            it.quantity = editedQuantity
                        }
                    })
                } else {
                    ShoppingListItem(
                        item = item,
                        onEditClick = { sItems = sItems.map { it.copy(isEditing = it.id == item.id) } },
                        onDeleteClick = { sItems = sItems - item }
                    )
                }
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (itemName.isNotEmpty() && itemQuantity.isNotEmpty()) {
                                val newItem = ShoppingItem(
                                    id = sItems.size + 1,
                                    name = itemName,
                                    quantity = itemQuantity.toInt()
                                )
                                sItems = sItems + newItem
                                itemName = ""
                                itemQuantity = ""
                                showDialog = false
                            }
                        },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Add Item")
                    }
                    Button(
                        onClick = { showDialog = false },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Cancel")
                    }
                }
            },
            title = {
                Text(
                    "Add Shopping Item",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        label = { Text("Item Name") },
                        value = itemName,
                        onValueChange = { itemName = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp)
                    )
                    OutlinedTextField(
                        label = { Text("Item Quantity") },
                        value = itemQuantity,
                        onValueChange = { itemQuantity = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun ShoppingItemEditor(item: ShoppingItem, onEditComplete: (String, Int) -> Unit) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isEditing) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.secondary), shape = RoundedCornerShape(12.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            OutlinedTextField(
                value = editedName,
                onValueChange = { editedName = it },
                label = { Text("Edit Name") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = editedQuantity,
                onValueChange = { editedQuantity = it },
                label = { Text("Edit Quantity") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
            )
        }
        Button(
            onClick = {
                isEditing = false
                onEditComplete(editedName, editedQuantity.toIntOrNull() ?: 1)
            },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Save")
        }
    }
}


@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(8.dp)
            .border(
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(
                text = "Item: ${item.name}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Qty: ${item.quantity}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(8.dp)
            )

            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.Red)
            }
        }
    }
}
