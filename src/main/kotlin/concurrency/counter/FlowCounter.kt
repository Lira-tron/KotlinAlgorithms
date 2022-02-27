package concurrency.counter

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FlowCounter: Counter() {
    override var counter: Int = 0
        get() = flow.value

    private val flow = MutableStateFlow(0)

    override suspend fun incrementCounter() {
        flow.update { it + 1 }
    }
}