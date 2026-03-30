package io.github.waqas028.nativecmp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.Dp
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIButton
import platform.UIKit.UIButtonConfiguration
import platform.UIKit.UIColor
import platform.UIKit.UIControlEventTouchUpInside
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
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
    NativeButtonInternal(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        containerColor = containerColor,
        contentColor = contentColor,
        style = "filled"
    )
}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
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
    NativeButtonInternal(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        containerColor = containerColor,
        contentColor = contentColor,
        style = "outlined"
    )
}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
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
    NativeButtonInternal(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        containerColor = containerColor,
        contentColor = contentColor,
        style = "plain"
    )
}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
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
    NativeButtonInternal(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        containerColor = containerColor,
        contentColor = contentColor,
        style = "gray"
    )
}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
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
    // For native icon button, we ideally want a native image. 
    // Since we only have a Composable icon, we wrap it.
    // For now, we use a simple button with no text.
    NativeButtonInternal(
        text = null,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        containerColor = containerColor,
        contentColor = contentColor,
        style = "plain"
    )
}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
@Composable
private fun NativeButtonInternal(
    text: String?,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    containerColor: Color?,
    contentColor: Color?,
    style: String
) {
    val target = remember(onClick) {
        object : NSObject() {
            @ObjCAction
            fun action() {
                onClick()
            }
        }
    }

    UIKitView(
        factory = {
            val config = when (style) {
                "filled" -> UIButtonConfiguration.filledButtonConfiguration()
                "outlined" -> UIButtonConfiguration.borderedButtonConfiguration()
                "plain" -> UIButtonConfiguration.plainButtonConfiguration()
                "gray" -> UIButtonConfiguration.grayButtonConfiguration()
                else -> UIButtonConfiguration.filledButtonConfiguration()
            }
            
            UIButton.buttonWithConfiguration(
                configuration = config,
                primaryAction = null
            ).apply {
                addTarget(
                    target = target,
                    action = NSSelectorFromString("action"),
                    forControlEvents = UIControlEventTouchUpInside
                )
            }
        },
        modifier = modifier,
        update = { button ->
            val config = button.configuration ?: UIButtonConfiguration.filledButtonConfiguration()
            
            text?.let { config.setTitle(it) }
            
            containerColor?.let {
                config.background.backgroundColor = it.toUIColor()
            }
            contentColor?.let {
                config.baseForegroundColor = it.toUIColor()
            }
            
            button.setConfiguration(config)
            button.enabled = enabled
        }
    )
}

private fun Color.toUIColor(): UIColor = UIColor(
    red = red.toDouble(),
    green = green.toDouble(),
    blue = blue.toDouble(),
    alpha = alpha.toDouble()
)
