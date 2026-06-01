package com.focos_dengue.ui.report_screen

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
import androidx.compose.ui.unit.times
import com.focos_dengue.ui.utils.LG
import com.focos_dengue.ui.utils.MD
import com.focos_dengue.ui.utils.SM
import com.focos_dengue.ui.utils.XS
import com.focos_dengue.ui.utils.XL
import com.focos_dengue.ui.utils.TEXT_SM
import com.focos_dengue.ui.components.ParagraphText
import com.focos_dengue.ui.components.PrimaryButton
import com.focos_dengue.ui.components.PrimaryCard
import com.focos_dengue.ui.components.SubtitleText
import com.focos_dengue.ui.components.TextFieldWithCounter
import com.focos_dengue.ui.components.TitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreenContent(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    var description by remember { mutableStateOf("") }
    val maxCharsOfDescription = 200

    Column(modifier = modifier.fillMaxSize().verticalScroll(scrollState).padding(2 * MD)) {
        TitleText("Novo reporte", marginTop = XS)

        ParagraphText(
            text = "Envie fotos do problema para os orgãos públicos",
            marginTop = SM,
            marginBottom = MD
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

        SubtitleText("Descrição", marginTop = MD, marginBottom = MD)

        TextFieldWithCounter(
            value = description,
            onValueChange = { description = it },
            maxChar = maxCharsOfDescription,
            height = 4 * XL,
            maxLines = 5,
            placeholder = "Descreva o problema encontrado..."
        )

        SubtitleText("Fotos do Problema", marginTop = MD, marginBottom = MD)

        PhotoCard(height = 8 * XL)

        Spacer(Modifier.height(MD))

        PrimaryCard {
            ParagraphText(
                text = "Essa denúncia será enviada para o seguinte destinatário:",
                fontSize = TEXT_SM,
                marginBottom = XS
            )
            ParagraphText("Centro de Controle de Zoonoses", fontSize = TEXT_SM)
        }

        Spacer(Modifier.height(LG))

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