package com.nativecmp.kit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
expect fun NativeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color? = null,
    contentColor: Color? = null,
    disabledContainerColor: Color? = null,
    disabledContentColor: Color? = null,
    shape: Shape? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues? = null,
    elevation: Dp? = null,
    icon: (@Composable () -> Unit)? = null,
    iconSpacing: Dp? = null
)

@Composable
expect fun NativeOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color? = null,
    contentColor: Color? = null,
    disabledContainerColor: Color? = null,
    disabledContentColor: Color? = null,
    shape: Shape? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues? = null,
    elevation: Dp? = null,
    icon: (@Composable () -> Unit)? = null,
    iconSpacing: Dp? = null
)

@Composable
expect fun NativeTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color? = null,
    contentColor: Color? = null,
    disabledContainerColor: Color? = null,
    disabledContentColor: Color? = null,
    shape: Shape? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues? = null,
    elevation: Dp? = null,
    icon: (@Composable () -> Unit)? = null,
    iconSpacing: Dp? = null
)

@Composable
expect fun NativeTonalButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color? = null,
    contentColor: Color? = null,
    disabledContainerColor: Color? = null,
    disabledContentColor: Color? = null,
    shape: Shape? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues? = null,
    elevation: Dp? = null,
    icon: (@Composable () -> Unit)? = null,
    iconSpacing: Dp? = null
)

@Composable
expect fun NativeIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color? = null,
    contentColor: Color? = null,
    disabledContainerColor: Color? = null,
    disabledContentColor: Color? = null,
    shape: Shape? = null,
    border: BorderStroke? = null,
    icon: @Composable () -> Unit
)
