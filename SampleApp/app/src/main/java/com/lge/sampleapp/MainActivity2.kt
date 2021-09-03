package com.lge.sampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lge.sampleapp.databinding.ListFragmentBinding
import com.lge.sampleapp.databinding.MainFragmentBinding
import com.lge.sampleapp.databinding.UserListItemBinding

// => Single Activity Architecture
// : 액티비티가 화면을 보여주는 역활을 수행하는 것이 아니라,
//   프래그먼트를 호스팅하는 역활을 수행합니다.
//   => 호스트 액티비티
//    : 프래그먼트를 호스팅할 영역을 지정합니다.

// View Binding
// => 기존의 레이아웃의 이름 규칙이 변경되는 것이 좋습니다.
//  activity_main.xml => ActivityMainBinding
//  main_activity.xml => MainActivityBinding
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        // 액티비티가 최초에 실행되었을 때
        if (savedInstanceState == null) {
            // val fragment = MainFragment()
            val fragment = ListFragment()

            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit()
        }
    }
}

/*
class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.main_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.button)
        val textView = view.findViewById<TextView>(R.id.textView)

        button.setOnClickListener {
            textView.text = "Hello, Kotlin"
        }

    }
}
*/

/*
class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null

    private val binding: MainFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return _binding?.root
    }

    // 화면이 사용자에게 보여지고, 인터렉션이 가능하다.
    override fun onStart() {
        super.onStart()

        binding.button.setOnClickListener {
            binding.textView.text = "Hello, Kotlin"
        }
    }

    // 주의사항!!
    // => 프래그먼트안에서
    //    프래그먼트의 수명과 프래그먼트의 뷰의 수명이 다릅니다.
    //  프래그먼트 생성
    //  뷰 보여짐 - 뷰 생성
    //  뷰 사라짐 - 뷰 파괴
    //  프래그먼트 파괴
    // => 바인딩 객체를 사용할 때, 뷰의 수명에 대한 관리가 직접 필요합니다.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
*/

// Binding 객체를 사용하는 방법
// 1. inflate + bind
//  _binding = MainFragmentBinding.inflate(
//    inflater,
//    container,
//    false
//  )
// _binding.root : 실제 인플레이팅된 View

// 2. bind
//   binding = MainFragmentBinding.bind(view)

// 부모의 생성자의 인자로 전달한 레이아웃 아이디에 대한 인플레이팅 처리가
// 되기 때문에, 굳이 onCreateView를 만들 필요가 없습니다.
class MainFragment : Fragment(R.layout.main_fragment) {
    private var binding: MainFragmentBinding? = null

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // 이미 인플레이팅이 완료되었으므로, bind만 하면 됩니다.
        binding = MainFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null       // !!!
    }
}

class ListFragment : Fragment(R.layout.list_fragment) {
    private var binding: ListFragmentBinding? = null

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListFragmentBinding.bind(view)
    }

    override fun onStart() {
        super.onStart()
        val binding = binding ?: return
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
        binding = null
    }
}


// ViewHolder
// Adapter
/*
class UserItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    /*
    var nameTextView: TextView
    var ageTextView: TextView
    init {
        nameTextView = view.findViewById(R.id.nameTextView)
        ageTextView = view.findViewById(R.id.ageTextView)
    }
    */
    private val nameTextView: TextView by lazy {
        view.findViewById(R.id.nameTextView)
    }

    private val ageTextView: TextView by lazy {
        view.findViewById(R.id.ageTextView)
    }

    fun bind(user: User) {
        nameTextView.text = user.name
        ageTextView.text = "${user.age}"
    }
}

class UserListAdapter : RecyclerView.Adapter<UserItemViewHolder>() {
    var items: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.user_list_item, parent, false
        )

        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.count()
}
*/

class UserItemViewHolder(private val binding: UserListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        with(binding) {
            nameTextView.text = user.name
            // ageTextView.text = "${user.age}"
            ageTextView.text = user.age.toString()
        }
    }
}

class UserListAdapter : RecyclerView.Adapter<UserItemViewHolder>() {
    var items: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        // * inflate + bind
        // val binding = UserListItemBinding.inflate(inflater, parent, false)
        // return UserItemViewHolder(binding)

        // * bind
        val view = inflater.inflate(
            R.layout.user_list_item, parent, false
        )
        val binding = UserListItemBinding.bind(view)


        return UserItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.count()
}



















