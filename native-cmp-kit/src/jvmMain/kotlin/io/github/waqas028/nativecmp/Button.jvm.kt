package io.github.waqas028.nativecmp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
actual fun NativeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    containerColor: Color?,
    contentColor: Color?,
    disabledContainerColor: Color?,
    disabledContentColor: Color?,
    shape: Shape?,
    border: BorderStroke?,
    contentPadding: PaddingValues?,
    elevation: Dp?,
    icon: (@Composable () -> Unit)?,
    iconSpacing: Dp?
) {
    val colors = ButtonDefaults.buttonColors(
        containerColor = containerColor ?: ButtonDefaults.buttonColors().containerColor,
        contentColor = contentColor ?: ButtonDefaults.buttonColors().contentColor,
        disabledContainerColor = disabledContainerColor ?: ButtonDefaults.buttonColors().disabledContainerColor,
        disabledContentColor = disabledContentColor ?: ButtonDefaults.buttonColors().disabledContentColor
    )

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape ?: ButtonDefaults.shape,
        colors = colors,
        elevation = elevation?.let { ButtonDefaults.buttonElevation(defaultElevation = it) },
        border = border,
        contentPadding = contentPadding ?: ButtonDefaults.ContentPadding
    ) {
        ButtonContent(text, icon, iconSpacing)
    }
}

@Composable
actual fun NativeOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    containerColor: Color?,
    contentColor: Color?,
    disabledContainerColor: Color?,
    disabledContentColor: Color?,
    shape: Shape?,
    border: BorderStroke?,
    contentPadding: PaddingValues?,
    elevation: Dp?,
    icon: (@Composable () -> Unit)?,
    iconSpacing: Dp?
) {
    val colors = ButtonDefaults.outlinedButtonColors(
        containerColor = containerColor ?: Color.Transparent,
        contentColor = contentColor ?: ButtonDefaults.outlinedButtonColors().contentColor,
        disabledContainerColor = disabledContainerColor ?: Color.Transparent,
        disabledContentColor = disabledContentColor ?: ButtonDefaults.outlinedButtonColors().disabledContentColor
    )

    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape ?: ButtonDefaults.outlinedShape,
        colors = colors,
        elevation = elevation?.let { ButtonDefaults.buttonElevation(defaultElevation = it) },
        border = border ?: ButtonDefaults.outlinedButtonBorder(enabled),
        contentPadding = contentPadding ?: ButtonDefaults.ContentPadding
    ) {
        ButtonContent(text, icon, iconSpacing)
    }
}

@Composable
actual fun NativeTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    containerColor: Color?,
    contentColor: Color?,
    disabledContainerColor: Color?,
    disabledContentColor: Color?,
    shape: Shape?,
    border: BorderStroke?,
    contentPadding: PaddingValues?,
    elevation: Dp?,
    icon: (@Composable () -> Unit)?,
    iconSpacing: Dp?
) {
    val colors = ButtonDefaults.textButtonColors(
        containerColor = containerColor ?: Color.Transparent,
        contentColor = contentColor ?: ButtonDefaults.textButtonColors().contentColor,
        disabledContainerColor = disabledContainerColor ?: Color.Transparent,
        disabledContentColor = disabledContentColor ?: ButtonDefaults.textButtonColors().disabledContentColor
    )

    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape ?: ButtonDefaults.textShape,
        colors = colors,
        elevation = elevation?.let { ButtonDefaults.buttonElevation(defaultElevation = it) },
        border = border,
        contentPadding = contentPadding ?: ButtonDefaults.TextButtonContentPadding
    ) {
        ButtonContent(text, icon, iconSpacing)
    }
}

@Composable
actual fun NativeTonalButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    containerColor: Color?,
    contentColor: Color?,
    disabledContainerColor: Color?,
    disabledContentColor: Color?,
    shape: Shape?,
    border: BorderStroke?,
    contentPadding: PaddingValues?,
    elevation: Dp?,
    icon: (@Composable () -> Unit)?,
    iconSpacing: Dp?
) {
    val colors = ButtonDefaults.filledTonalButtonColors(
        containerColor = containerColor ?: ButtonDefaults.filledTonalButtonColors().containerColor,
        contentColor = contentColor ?: ButtonDefaults.filledTonalButtonColors().contentColor,
        disabledContainerColor = disabledContainerColor ?: ButtonDefaults.filledTonalButtonColors().disabledContainerColor,
        disabledContentColor = disabledContentColor ?: ButtonDefaults.filledTonalButtonColors().disabledContentColor
    )

    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape ?: ButtonDefaults.filledTonalShape,
        colors = colors,
        elevation = elevation?.let { ButtonDefaults.buttonElevation(defaultElevation = it) },
        border = border,
        contentPadding = contentPadding ?: ButtonDefaults.ContentPadding
    ) {
        ButtonContent(text, icon, iconSpacing)
    }
}

@Composable
actual fun NativeIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    containerColor: Color?,
    contentColor: Color?,
    disabledContainerColor: Color?,
    disabledContentColor: Color?,
    shape: Shape?,
    border: BorderStroke?,
    icon: @Composable () -> Unit
) {
    val colors = IconButtonDefaults.iconButtonColors(
        containerColor = containerColor ?: Color.Transparent,
        contentColor = contentColor ?: IconButtonDefaults.iconButtonColors().contentColor,
        disabledContainerColor = disabledContainerColor ?: Color.Transparent,
        disabledContentColor = disabledContentColor ?: IconButtonDefaults.iconButtonColors().disabledContentColor
    )

    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors
    ) {
        icon()
    }
}

@Composable
private fun ButtonContent(
    text: String,
    icon: (@Composable () -> Unit)?,
    iconSpacing: Dp?
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (icon != null) {
            icon()
            Spacer(Modifier.width(iconSpacing ?: 8.dp))
        }
        Text(text = text)
    }
}
