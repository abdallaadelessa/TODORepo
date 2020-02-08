package de.check24.todo

import de.check24.todo.pojo.Todo
import java.util.*

/**
 * @author Created by Abdullah Essa on 08.02.20.
 */
object DataProvider {

    val todoList = mutableListOf<Todo>().apply {
        repeat(20) {
            add(Todo("Todo$it", "Description", "https://miro.medium.com/max/1024/1*Er24qsvJdqLofK-sK0QzpA.jpeg", Date()))
        }
    }

}
