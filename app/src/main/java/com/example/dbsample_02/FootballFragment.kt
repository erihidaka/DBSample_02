package com.example.dbsample_02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.dbsample_02.databinding.FragmentFootballBinding

class FootballFragment : Fragment() {

    private lateinit var binding: FragmentFootballBinding

    private val viewModel = FootballViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFootballBinding.inflate(inflater, container, false)
        // このようにしても良い
//        binding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_football, container, false
//        )
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isEnabled.observe(viewLifecycleOwner, Observer { isEnabled ->
            binding.button.isEnabled = isEnabled

        })

        binding.button.setOnClickListener {
            val text = binding.editText.text.toString()
            viewModel.submitText(text)
        }

        // この部分はDataBindingで実装.
        // 代わりに、レイアウトファイルのandroid:enabled="@{vm.isEnabled}"の行で実装できる！
//        viewModel.isEnabled.observe(viewLifecycleOwner, Observer { isEnabled ->
//            binding.button.isEnabled = isEnabled
//        })
    }
}
    /*private val _buttonText: MutableLiveData<String> =
        MutableLiveData<String>().also { mutableLiveData ->
            mutableLiveData.value = "Ready"
        }
    val buttonText: LiveData<String>
        get() = _buttonText
    */