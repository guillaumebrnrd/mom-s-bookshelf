package com.guillaume.bernard.mombookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.guillaume.bernard.mombookshelf.ui.MomsBookshelfApp
import com.guillaume.bernard.mombookshelf.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MomsBookshelfApp()
            }
        }
    }
}

