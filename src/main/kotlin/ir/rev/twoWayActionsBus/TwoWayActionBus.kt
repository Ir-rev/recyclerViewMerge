package ir.rev.twoWayActionsBus

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject

/**
 * Шина событий или действий.
 *
 * @author au.pervov
 */
class TwoWayActionBus<A : TwoWayAction> {

    /**
     * Обработчик действия со стороны фрагмента.
     */
    var handler: ((A) -> Unit)? = null

    /**
     * Сабжект действия.
     */
    private val action: PublishSubject<A> = PublishSubject.create()

    /**
     * Текущее отправленное действие.
     */
    var current: A? = null
        private set

    /**
     * Совершено новое действие.
     * @param action экземпляр действия.
     */
    fun post(action: A) {
        current = action
        this.action.onNext(action)
    }

    /**
     * Производит подписку на [action].
     */
    internal fun subscribe(actionHandler: (A) -> Unit, errorHandler: (Throwable) -> Unit): Disposable =
        action.subscribe(
            {
                actionHandler(it)
                handler?.invoke(it)
            },
            errorHandler
        )
}