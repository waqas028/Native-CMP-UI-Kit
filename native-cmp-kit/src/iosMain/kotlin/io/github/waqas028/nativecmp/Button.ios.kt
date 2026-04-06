package io.github.waqas028.nativecmp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.platform.LocalLayoutDirection
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.cValue
import platform.CoreGraphics.CGRectGetHeight
import platform.CoreGraphics.CGRectGetWidth
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIButton
import platform.UIKit.UIButtonConfiguration
import platform.UIKit.UIColor
import platform.UIKit.UIControlEventTouchUpInside
import platform.darwin.NSObject

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
        style = NativeButtonStyle.Filled,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        shape = shape,
        border = border,
        contentPadding = contentPadding,
        elevation = elevation,
        iconSpacing = iconSpacing
    )
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
    NativeButtonInternal(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = NativeButtonStyle.Outlined,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        shape = shape,
        border = border,
        contentPadding = contentPadding,
        elevation = elevation,
        iconSpacing = iconSpacing
    )
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
    NativeButtonInternal(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = NativeButtonStyle.Text,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        shape = shape,
        border = border,
        contentPadding = contentPadding,
        elevation = elevation,
        iconSpacing = iconSpacing
    )
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
    NativeButtonInternal(
        text = text,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = NativeButtonStyle.Tonal,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        shape = shape,
        border = border,
        contentPadding = contentPadding,
        elevation = elevation,
        iconSpacing = iconSpacing
    )
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
    NativeButtonInternal(
        text = "",
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        style = NativeButtonStyle.Icon,
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        shape = shape,
        border = border,
        contentPadding = PaddingValues(),
        elevation = null,
        iconSpacing = null
    )
}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
@Composable
private fun NativeButtonInternal(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    style: NativeButtonStyle,
    containerColor: Color?,
    contentColor: Color?,
    disabledContainerColor: Color?,
    disabledContentColor: Color?,
    shape: Shape?,
    border: BorderStroke?,
    contentPadding: PaddingValues?,
    elevation: Dp?,
    iconSpacing: Dp?
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val latestOnClick by rememberUpdatedState(onClick)
    val target = remember {
        ButtonActionTarget {
            latestOnClick()
        }
    }

    UIKitView(
        factory = {
            UIButton.buttonWithConfiguration(
                configuration = style.defaultConfiguration(),
                primaryAction = null
            ).apply {
                addTarget(
                    target = target,
                    action = NSSelectorFromString("handleTap"),
                    forControlEvents = UIControlEventTouchUpInside
                )
            }
        },
        modifier = modifier,
        update = { button ->
            button.layoutIfNeeded()

            val resolvedContainerColor = if (enabled) {
                containerColor
            } else {
                disabledContainerColor ?: containerColor
            }
            val resolvedContentColor = if (enabled) {
                contentColor
            } else {
                disabledContentColor ?: disabledContentColorForStyle(style, contentColor)
            }

            val configuration = (button.configuration ?: style.defaultConfiguration()).apply {
                title = if (style == NativeButtonStyle.Icon) null else text
                baseBackgroundColor = resolvedContainerColor?.toUIColor()
                baseForegroundColor = resolvedContentColor?.toUIColor()
                contentInsets = contentPadding.toDirectionalInsets(density, layoutDirection, style)
                if (iconSpacing != null) {
                    imagePadding = iconSpacing.value.toDouble()
                }
            }

            button.configuration = configuration
            button.enabled = enabled
            button.alpha = if (enabled) 1.0 else 0.55
            applyBorder(button, style, enabled, border, contentColor, density)
            applyShape(button, shape, density, layoutDirection, style)
            applyShadow(button, style, enabled, elevation, resolvedContainerColor, density)
        }
    )
}

private enum class NativeButtonStyle {
    Filled,
    Outlined,
    Text,
    Tonal,
    Icon
}

@OptIn(ExperimentalForeignApi::class)
private fun NativeButtonStyle.defaultConfiguration(): UIButtonConfiguration = when (this) {
    NativeButtonStyle.Filled -> UIButtonConfiguration.filledButtonConfiguration()
    NativeButtonStyle.Outlined -> UIButtonConfiguration.borderedButtonConfiguration()
    NativeButtonStyle.Text -> UIButtonConfiguration.plainButtonConfiguration()
    NativeButtonStyle.Tonal -> UIButtonConfiguration.tintedButtonConfiguration()
    NativeButtonStyle.Icon -> UIButtonConfiguration.plainButtonConfiguration()
}

private fun disabledContentColorForStyle(
    style: NativeButtonStyle,
    contentColor: Color?
): Color? = when {
    contentColor != null -> contentColor.copy(alpha = 0.45f)
    style == NativeButtonStyle.Filled -> Color.White.copy(alpha = 0.7f)
    else -> null
}

@OptIn(ExperimentalForeignApi::class)
private fun applyBorder(
    button: UIButton,
    style: NativeButtonStyle,
    enabled: Boolean,
    border: BorderStroke?,
    contentColor: Color?,
    density: Density
) {
    val resolvedBorder = border?.let {
        BorderAppearance(
            width = with(density) { it.width.toPx().toDouble() },
            color = (it.brush as? SolidColor)?.value?.toUIColor()
        )
    } ?: if (style == NativeButtonStyle.Outlined) {
        BorderAppearance(
            width = 1.0,
            color = (contentColor ?: Color(0xFF007AFF)).toUIColor()
        )
    } else {
        null
    }

    button.layer.borderWidth = if (enabled) resolvedBorder?.width ?: 0.0 else 0.0
    button.layer.borderColor = resolvedBorder?.color?.CGColor
}

@OptIn(ExperimentalForeignApi::class)
private fun applyShape(
    button: UIButton,
    shape: Shape?,
    density: Density,
    layoutDirection: LayoutDirection,
    style: NativeButtonStyle
) {
    val width = CGRectGetWidth(button.bounds)
    val height = CGRectGetHeight(button.bounds)
    val fallbackCornerRadius = when (style) {
        NativeButtonStyle.Icon -> minOf(width, height) / 2.0
        else -> 12.0
    }

    if (shape == null || width <= 0.0 || height <= 0.0) {
        button.layer.cornerRadius = fallbackCornerRadius
        return
    }

    val outline = shape.createOutline(
        size = Size(width.toFloat(), height.toFloat()),
        layoutDirection = layoutDirection,
        density = density
    )

    button.layer.cornerRadius = when (outline) {
        is Outline.Rounded -> outline.roundRect.topLeftCornerRadius.x.toDouble()
        else -> fallbackCornerRadius
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun applyShadow(
    button: UIButton,
    style: NativeButtonStyle,
    enabled: Boolean,
    elevation: Dp?,
    containerColor: Color?,
    density: Density
) {
    val shadowRadius = when {
        !enabled -> 0.0
        elevation != null -> with(density) { elevation.toPx().toDouble() }
        style == NativeButtonStyle.Filled || style == NativeButtonStyle.Tonal -> 3.0
        else -> 0.0
    }

    if (shadowRadius <= 0.0) {
        button.layer.shadowOpacity = 0f
        button.layer.shadowRadius = 0.0
        button.layer.shadowOffset = platform.CoreGraphics.CGSizeMake(0.0, 0.0)
        return
    }

    button.layer.shadowColor = (containerColor ?: Color.Black.copy(alpha = 0.18f)).toUIColor().CGColor
    button.layer.shadowOpacity = 0.18f
    button.layer.shadowRadius = shadowRadius
    button.layer.shadowOffset = platform.CoreGraphics.CGSizeMake(0.0, shadowRadius / 2.0)
}

@OptIn(ExperimentalForeignApi::class)
private fun PaddingValues?.toDirectionalInsets(
    density: Density,
    layoutDirection: LayoutDirection,
    style: NativeButtonStyle
) = if (this == null) {
    when (style) {
        NativeButtonStyle.Text -> directionalInsets(8.0, 12.0, 8.0, 12.0)
        NativeButtonStyle.Icon -> directionalInsets(8.0, 8.0, 8.0, 8.0)
        else -> directionalInsets(10.0, 16.0, 10.0, 16.0)
    }
} else {
    with(density) {
        directionalInsets(
            this@toDirectionalInsets.calculateTopPadding().toPx().toDouble(),
            this@toDirectionalInsets.calculateLeftPadding(layoutDirection).toPx().toDouble(),
            this@toDirectionalInsets.calculateBottomPadding().toPx().toDouble(),
            this@toDirectionalInsets.calculateRightPadding(layoutDirection).toPx().toDouble()
        )
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun directionalInsets(
    top: Double,
    leading: Double,
    bottom: Double,
    trailing: Double
) = cValue<platform.UIKit.NSDirectionalEdgeInsets> {
    this.top = top
    this.leading = leading
    this.bottom = bottom
    this.trailing = trailing
}

private data class BorderAppearance(
    val width: Double,
    val color: UIColor?
)

@OptIn(BetaInteropApi::class)
private class ButtonActionTarget(
    private val onTap: () -> Unit
) : NSObject() {
    @ObjCAction
    fun handleTap() {
        onTap()
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun Color.toUIColor(): UIColor = UIColor(
    red = red.toDouble(),
    green = green.toDouble(),
    blue = blue.toDouble(),
    alpha = alpha.toDouble()
)
