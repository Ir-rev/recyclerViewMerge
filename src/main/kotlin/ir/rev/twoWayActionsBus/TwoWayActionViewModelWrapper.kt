package ir.rev.twoWayActionsBus

import android.util.Log
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Вью-модель-обёртка для обмена сообщениями только между фрагментом и вью-моделью, общая шина. Позволяет не иметь
 * двусторонних связей между фрагментом и вью-моделью.
 * Использовать в качестве делегата внутри главных вью-моделей экранов следущим образом:
 * - Проксировать [action] на сторону фрагмента.
 * - Вызвать [dispose] в [androidx.lifecycle.ViewModel.onCleared] или других местах для освобождения ресурсов и отписки.
 * - На стороне фрагмента задать [TwoWayActionBus.handler] для подписки, null для отписки в на стороне фрагмента если требуется
 * сделать это раньше чем произойдёт [androidx.lifecycle.ViewModel.onCleared].
 * @param A тип действия, см. [TwoWayAction].
 * @param handleAction обработчик действия со стороны вью-модели, принимает экземпляр действия в качестве аргумента.
 *
 * @author au.pervov
 */
class TwoWayActionViewModelWrapper<A : TwoWayAction>(
    handleAction: (A) -> Unit
) {

    /**
     * Подписка на действие.
     */
    private val actionDisposable: Disposable

    /**
     * Экзмемпляр шины событий или действий.
     */
    val action = TwoWayActionBus<A>()

    init {
        actionDisposable = action.subscribe(
            handleAction
        ) { error -> Log.e("checkResult", "handleErrorOrCrash: $error") }
    }

    /**
     * Освобождает связанные ресурсы.
     */
    fun dispose() {
        action.handler = null
        actionDisposable.dispose()
    }
}