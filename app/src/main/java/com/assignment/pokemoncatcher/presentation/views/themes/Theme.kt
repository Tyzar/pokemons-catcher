import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.assignment.pokemoncatcher.presentation.views.themes.errorContainerDark
import com.assignment.pokemoncatcher.presentation.views.themes.errorContainerLight
import com.assignment.pokemoncatcher.presentation.views.themes.errorDark
import com.assignment.pokemoncatcher.presentation.views.themes.errorLight
import com.assignment.pokemoncatcher.presentation.views.themes.onErrorContainerDark
import com.assignment.pokemoncatcher.presentation.views.themes.onErrorContainerLight
import com.assignment.pokemoncatcher.presentation.views.themes.onErrorDark
import com.assignment.pokemoncatcher.presentation.views.themes.onErrorLight
import com.assignment.pokemoncatcher.presentation.views.themes.onPrimaryContainerDark
import com.assignment.pokemoncatcher.presentation.views.themes.onPrimaryContainerLight
import com.assignment.pokemoncatcher.presentation.views.themes.onPrimaryDark
import com.assignment.pokemoncatcher.presentation.views.themes.onPrimaryLight
import com.assignment.pokemoncatcher.presentation.views.themes.onSecondaryContainerDark
import com.assignment.pokemoncatcher.presentation.views.themes.onSecondaryContainerLight
import com.assignment.pokemoncatcher.presentation.views.themes.onSecondaryDark
import com.assignment.pokemoncatcher.presentation.views.themes.onSecondaryLight
import com.assignment.pokemoncatcher.presentation.views.themes.onSurfaceDark
import com.assignment.pokemoncatcher.presentation.views.themes.onSurfaceLight
import com.assignment.pokemoncatcher.presentation.views.themes.onTertiaryContainerDark
import com.assignment.pokemoncatcher.presentation.views.themes.onTertiaryContainerLight
import com.assignment.pokemoncatcher.presentation.views.themes.onTertiaryDark
import com.assignment.pokemoncatcher.presentation.views.themes.onTertiaryLight
import com.assignment.pokemoncatcher.presentation.views.themes.outlineDark
import com.assignment.pokemoncatcher.presentation.views.themes.outlineLight
import com.assignment.pokemoncatcher.presentation.views.themes.primaryContainerDark
import com.assignment.pokemoncatcher.presentation.views.themes.primaryContainerLight
import com.assignment.pokemoncatcher.presentation.views.themes.primaryDark
import com.assignment.pokemoncatcher.presentation.views.themes.primaryLight
import com.assignment.pokemoncatcher.presentation.views.themes.scrimDark
import com.assignment.pokemoncatcher.presentation.views.themes.scrimLight
import com.assignment.pokemoncatcher.presentation.views.themes.secondaryContainerDark
import com.assignment.pokemoncatcher.presentation.views.themes.secondaryContainerLight
import com.assignment.pokemoncatcher.presentation.views.themes.secondaryDark
import com.assignment.pokemoncatcher.presentation.views.themes.secondaryLight
import com.assignment.pokemoncatcher.presentation.views.themes.shapes
import com.assignment.pokemoncatcher.presentation.views.themes.surfaceDark
import com.assignment.pokemoncatcher.presentation.views.themes.surfaceLight
import com.assignment.pokemoncatcher.presentation.views.themes.tertiaryContainerDark
import com.assignment.pokemoncatcher.presentation.views.themes.tertiaryContainerLight
import com.assignment.pokemoncatcher.presentation.views.themes.tertiaryDark
import com.assignment.pokemoncatcher.presentation.views.themes.tertiaryLight
import com.assignment.pokemoncatcher.presentation.views.themes.typography

val lightColorScheme =
    lightColorScheme(
        primary = primaryLight,
        onPrimary = onPrimaryLight,
        secondary = secondaryLight,
        onSecondary = onSecondaryLight,
        tertiary = tertiaryLight,
        onTertiary = onTertiaryLight,
        error = errorLight,
        onError = onErrorLight,
        primaryContainer = primaryContainerLight,
        onPrimaryContainer = onPrimaryContainerLight,
        secondaryContainer = secondaryContainerLight,
        onSecondaryContainer = onSecondaryContainerLight,
        tertiaryContainer = tertiaryContainerLight,
        onTertiaryContainer = onTertiaryContainerLight,
        errorContainer = errorContainerLight,
        onErrorContainer = onErrorContainerLight,
        surface = surfaceLight,
        onSurface = onSurfaceLight,
        outline = outlineLight,
        scrim = scrimLight
    )

val darkColorScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    error = errorDark,
    onError = onErrorDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    outline = outlineDark,
    scrim = scrimDark
)

@Composable
fun PokemonAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val selectedColorScheme =
        if (useDarkTheme) darkColorScheme
        else lightColorScheme
    MaterialTheme(
        colorScheme = selectedColorScheme,
        content = content,
        typography = typography,
        shapes = shapes
    )
}