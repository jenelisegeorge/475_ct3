 

package com.code.todo

import android.app.Application
import com.example.ToDo.data.AppContainer
import com.example.ToDo.data.AppDataContainer

class ToDoApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
