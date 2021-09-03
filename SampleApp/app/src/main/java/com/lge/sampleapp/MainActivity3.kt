package com.lge.sampleapp

import android.app.Activity
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.location.LocationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.lge.sampleapp.databinding.ActivityMainBinding
import com.lge.sampleapp.databinding.ListFragmentBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// 코틀린에서 뷰바인딩을 사용하면서 발생하는 보일러플레이트를
// 프로퍼티 위임 객체를 통해 제거하는 방법이 있습니다.
//  => 공식 라이브러리가 아닙니다.
class ActivityBindingDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
    val activity: Activity
) {
    private var binding: T? = null

    operator fun getValue(thisRef: Activity, property: KProperty<*>): T {
        // if (binding != null)
        //    return binding!!
        binding?.let {
            return it
        }

        // 1. inflate 메소드 찾기
        // Class<T> -> ActivityMainBinding::class.java
        // => 이 클래스 안에서 메소드를 찾아서 호출합니다.
        //  Binding.inflate(LayoutInflator)
        val inflateMethod = bindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java
        )

        // 2. inflate 메소드 호출을 통해 바인딩 객체 생성하기
        // Unchecked cast: Any! to T
        @Suppress("UNCHECKED_CAST")
        binding = inflateMethod.invoke(null, thisRef.layoutInflater) as T
        return binding!!
    }
}

/*
inline fun <reified T : ViewBinding> Activity.viewBinding(): ActivityBindingDelegate<T> {
    return ActivityBindingDelegate(
        T::class.java,
        this
    )
}
*/
inline fun <reified T : ViewBinding> Activity.viewBinding() = ActivityBindingDelegate(
    T::class.java,
    this
)

class MainActivity3 : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()
    /*
    private val binding: ActivityMainBinding by ActivityBindingDelegate(
        ActivityMainBinding::class.java,
        this
    )
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        /*
        // binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            binding.textView.text = "Hello"
        }
        */
        if (savedInstanceState == null) {
            val fragment = ListFragment2()

            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit()
        }
    }
}

// FragmentViewBindingDelegate
class FragmentViewBindingDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
    fragment: Fragment
) {
    private var binding: T? = null

    companion object {
        const val TAG = "FragmentViewBindingDelegate"
    }

    init {
        // owner: 관찰을 언제까지 할것인가?
        //        프래그먼트가 유효할 때까지 관찰을 수행하겠다.
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifeCycleOwner ->
            // viewLifeCycleOwner - 뷰의 생애주기에 대한 관찰이 가능합니다.
            viewLifeCycleOwner.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroyView() {
                    Log.i(TAG, "onDestroyView")
                    binding = null
                }
            })
        }
    }

    operator fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        binding?.let { return it }

        // 프래그먼트에서 뷰 바인딩을 사용할 때
        // bind(View) - 메소드를 찾습니다.
        val bindMethod = bindingClass.getMethod("bind", View::class.java)

        @Suppress("UNCHECKED_CAST")
        binding = bindMethod.invoke(null, thisRef.requireView()) as T
        // thisRef.requireView() - 프래그먼트에 인플레이팅된 뷰가 존재하지 않으면 IllegalStateException이 발생합니다.
        return binding!!
    }
}

inline fun <reified T : ViewBinding> Fragment.viewBinding() = FragmentViewBindingDelegate(
    T::class.java,
    this
)


class ListFragment2 : Fragment(R.layout.list_fragment) {
    companion object {
        const val TAG = "ListFragment2"
    }

    /*
    private val binding: ListFragmentBinding by FragmentViewBindingDelegate(
        ListFragmentBinding::class.java,
        this
    )
    */
    private val binding: ListFragmentBinding by viewBinding()

    /*
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListFragmentBinding.bind(view)
    }
    */

    override fun onStart() {
        super.onStart()
        // val binding = binding ?: return
        val context = context ?: return

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = UserListAdapter().apply {
                items = listOf(
                    User("Tom", 42),
                    User("Bob", 42),
                    User("Alice", 42),
                    User("Tim", 42),
                    User("David", 42),
                    User("Tom", 42),
                    User("Tom", 42),
                )
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // binding = null
        Log.i(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

}


inline fun <reified T : ViewBinding> ViewGroup.viewBinding() =
    ViewBindingDelegate(T::class.java, this)

class ViewBindingDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
    val view: ViewGroup
) : ReadOnlyProperty<ViewGroup, T> {
    private var binding: T? = null

    override fun getValue(thisRef: ViewGroup, property: KProperty<*>): T {
        binding?.let { return it }

        @Suppress("UNCHECKED_CAST")
        binding = try {
            val inflateMethod =
                bindingClass.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java)
            inflateMethod.invoke(null, LayoutInflater.from(thisRef.context), thisRef)
        } catch (e: NoSuchMethodException) {
            val inflateMethod = bindingClass.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            )
            inflateMethod.invoke(null, LayoutInflater.from(thisRef.context), thisRef, true) as T
        } as T
        return binding!!
    }
}