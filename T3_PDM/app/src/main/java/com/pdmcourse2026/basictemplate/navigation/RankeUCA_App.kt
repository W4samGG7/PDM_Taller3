package com.pdmcourse2026.basictemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdmcourse2026.basictemplate.navigation.Routes
import com.pdmcourse2026.basictemplate.screens.home.HomeScreen
import com.pdmcourse2026.basictemplate.screens.options.OptionsScreen
import com.pdmcourse2026.basictemplate.screens.questions.QuestionScreen
import com.pdmcourse2026.basictemplate.screens.resultados.ResultadosScreen

@Composable
fun RankeUCA_App() {
  val backStack = rememberNavBackStack(Routes.Questions)

  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.Home> {
        HomeScreen(navigateToResultados = {
          backStack.add(Routes.Result)
        })
      }
      entry<Routes.Result> {
        ResultadosScreen(navigateToResultados = {
          backStack.add(Routes.Home)
        })
      }
      entry<Routes.Options> { key->
        OptionsScreen(key.questionId)
      }
      entry<Routes.Questions> {
        QuestionScreen(navigateToDetail = { questionId ->
          backStack.add(Routes.Options(questionId))
        })
      }
    },
  )


}