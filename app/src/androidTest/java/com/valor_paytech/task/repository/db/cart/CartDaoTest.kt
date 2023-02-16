package com.valor_paytech.task.repository.db.cart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.valor_paytech.task.repository.db.AppDatabase
import com.valor_paytech.task.repository.model.cart.Cart
import dagger.hilt.android.testing.HiltAndroidRule
//import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
internal class CartDaoTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test-db")
    lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        hiltRule.inject()
        userDao = database.cartDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertUser() = runBlockingTest {
        var cartItem: Cart =
            Cart(
                //id = it.id,
                name = "Jaha",
                productId = "22",
                qty = 1,
                image ="test",
                price = "Test",
            )

        userDao.insert(cartItem)
       /* val allUsers = userDao.getAllCartItems()
        assertThat(allUsers).contains(userDao)*/

    }
}