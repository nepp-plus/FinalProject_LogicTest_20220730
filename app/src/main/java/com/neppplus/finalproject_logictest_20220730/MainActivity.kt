package com.neppplus.finalproject_logictest_20220730

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.neppplus.finalproject_logictest_20220730.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    val mLottoNumbers = ArrayList<Int>() // 당첨번호 6개를 담을 목록
    val mLottoNumTextViews = ArrayList<TextView>() // 표시용 텍스트 6개를 담을 목록

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        당첨 확인 버튼이 눌리면 => 1~45중 하나의 숫자를 뽑아 텍스트뷰 6개에 각각 표시

        binding.btnCheckLottoNum.setOnClickListener {

//            랜덤 숫자 6개를 채우기 전에, 기존의 숫자 목록은 비우고 채우자.
            mLottoNumbers.clear()

//            랜덤 숫자 (1~45) 6개를 먼저 생성 > 목록에 담아두자. (숫자 6개를 담을 목록도 필요함)
//            랜덤 숫자를 6회 반복 생성
            
            for (i in 0..5) {
//                단순 6회 반복 for문

//                이미 뽑아둔 숫자와 중복인 숫자가 랜덤으로 나온다면, 무효처리. 다시 뽑자.
//                제대로 된 숫자가 나올때까지 무한 반복으로 랜덤 뽑자.

                while (true) {

//                1~45 중의 숫자 하나 랜덤 추출
                    val randomNum = (1..45).random()

//                    뽑아둔 숫자중에 같은게 없다면? (중복 X) 목록으로 추가,
//                    무한반복 종료 (다음 숫자 뽑으러 넘어가자)

                    if ( !mLottoNumbers.contains( randomNum ) ) {

//                     중복이 아니니 당첨번호 목록으로 추가 (0~5번칸  채움)
                        mLottoNumbers.add( randomNum )
                        break  // 나를 감싸는 반복문을 깨고 탈출 => 무한 반복 종료
                    }
                }
            }
            

//            담아둔 숫자를 정리. (작은 수 ~ 큰 수로 정렬 : order)

            mLottoNumbers.sort() // 코틀린 언어가 제공하는 정렬 기능 활용


//            텍스트뷰 6개를 반복적으로 처리 : 반복문 이용
//            txt01, txt02, txt03... 등등 텍스트뷰 목록을 만들고 하나씩 꺼내다 쓰자.
//            for 활용 > 목록에서 하나씩 꺼내주는 역할.

            mLottoNumTextViews.forEachIndexed { index, lottoNumTxt ->

//                lottoNumTxt 변수에는, txtLottoNum01~06이 순서대로 담기면서 반복 실행됨.
//                + index 변수에는 지금이 몇바퀴째인지 (0,1,2,..,5) 의 숫자가 담겨있게 됨.


//                랜덤을 뽑자마자 > 텍스트뷰에 배치하면, 작은 수 ~ 큰 수 대로 정리되지 않음.
//                별도의 for문을 통해서, 6개의 랜덤 숫자를 만들고 > 정리 한 다음 에 > 텍스트뷰에 반영만 따로 반복.

//                당첨번호 6개를 순서대로 뽑아서 표시
//                반복 순서에 맞는 위치의 랜덤값을 찾아서 텍스트뷰에 순서대로 반영

                lottoNumTxt.text = mLottoNumbers[index].toString()

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