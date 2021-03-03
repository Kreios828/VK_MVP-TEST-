package space.kreios.ru.presenters

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import space.kreios.ru.R
import space.kreios.ru.views.LoginView


@InjectViewState
class LoginPresenter: MvpPresenter<LoginView>() {
    fun login(isSuccess: Boolean) {
        viewState.startLoading()
        Handler().postDelayed({
            viewState.endLoading()
            if (isSuccess) {
                viewState.openFriends()
            } else {
                viewState.showError(textResource = R.string.login_error_credentials)
            }

        }, 500)
    }
}
