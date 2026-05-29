package com.foco_acessibilidade_dengue.ui.report_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foco_acessibilidade_dengue.ui.components.ParagraphText
import com.foco_acessibilidade_dengue.ui.components.PrimaryButton
import com.foco_acessibilidade_dengue.ui.components.PrimaryCard
import com.foco_acessibilidade_dengue.ui.components.PrimaryText
import com.foco_acessibilidade_dengue.ui.components.SubtitleText
import com.foco_acessibilidade_dengue.ui.components.TitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreenContent(modifier: Modifier = Modifier) {
//    var categoryDropdownIsExpanded by remember { mutableStateOf(false) }
//    var selectedCategory by remember { mutableStateOf("Dengue") }
    val scrollState = rememberScrollState()
    var description by remember { mutableStateOf("") }
    val maxCharsOfDescription = 200

    Column(modifier = modifier.fillMaxSize().verticalScroll(scrollState).padding(24.dp)) {
        TitleText("Novo reporte", marginTop = 4.dp)

        ParagraphText(
            text = "Envie fotos do problema para os orgãos públicos",
            marginTop = 8.dp,
            marginBottom = 12.dp
        )

        LocationCard()

        // SubtitleText("Categoria do Problema", marginTop = 12.dp, marginBottom = 12.dp)

//        Select(
//            expanded = categoryDropdownIsExpanded,
//            setExpanded = { categoryDropdownIsExpanded = it },
//            selectedValue = selectedCategory,
//            setSelectedValue = { selectedCategory = it },
//            options = listOf("Dengue"),
//            label = "Selecione uma categoria"
//        )

        SubtitleText("Descrição", marginTop = 12.dp, marginBottom = 12.dp)

        TextFieldWithCounter(
            value = description,
            onValueChange = { description = it },
            maxChar = maxCharsOfDescription,
            height = 128.dp,
            maxLines = 5,
            placeholder = "Descreva o problema encontrado..."
        )

        SubtitleText("Fotos do Problema", marginTop = 12.dp, marginBottom = 12.dp)

        PhotoCard(height = 128.dp)

        Spacer(Modifier.height(12.dp))

        PrimaryCard {
            SubtitleText("Destinatários")
            ParagraphText(
                text = "Essa denúncia será enviada para o seguinte destinatário:",
                fontSize = 14.sp,
                marginBottom = 4.dp
            )
            PrimaryText("Centro de Controle de Zoonoses")
        }

        Spacer(Modifier.height(16.dp))

        PrimaryButton("Enviar Denúncia") {

        }
    }
}

@Preview
@Composable
fun ReportScreenContentPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ReportScreenContent()
        }
    }
}