package com.neppplus.finalproject_logictest_20220730

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.neppplus.finalproject_logictest_20220730.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    val mLottoNumTextViews = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        당첨 확인 버튼이 눌리면 => 1~45중 하나의 숫자를 뽑아 텍스트뷰 6개에 각각 표시

        binding.btnCheckLottoNum.setOnClickListener {

//            텍스트뷰 6개를 반복적으로 처리 : 반복문 이용
//            txt01, txt02, txt03... 등등 텍스트뷰 목록을 만들고 하나씩 꺼내다 쓰자.
//            for 활용 > 목록에서 하나씩 꺼내주는 역할.

            mLottoNumTextViews.forEachIndexed { index, lottoNumTxt ->

//                lottoNumTxt 변수에는, txtLottoNum01~06이 순서대로 담기면서 반복 실행됨.
//                + index 변수에는 지금이 몇바퀴째인지 (0,1,2,..,5) 의 숫자가 담겨있게 됨.

//                1~45의 랜덤 숫자를 뽑자.

                val randomNum = (1..45).random()

                lottoNumTxt.text = randomNum.toString()

            }



        }


    }

    override fun setValues() {

//        텍스트뷰 6개를 목록에 담아두자.
        mLottoNumTextViews.add( binding.txtLottoNum01 )
        mLottoNumTextViews.add( binding.txtLottoNum02 )
        mLottoNumTextViews.add( binding.txtLottoNum03 )
        mLottoNumTextViews.add( binding.txtLottoNum04 )
        mLottoNumTextViews.add( binding.txtLottoNum05 )
        mLottoNumTextViews.add( binding.txtLottoNum06 )

    }
}