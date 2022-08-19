package com.han.my_friend_kim_jung_san.ui.meeting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.han.my_friend_kim_jung_san.R
import com.han.my_friend_kim_jung_san.data.entity.Room
import com.han.my_friend_kim_jung_san.data.entity.User
import com.han.my_friend_kim_jung_san.data.remote.room.RoomService
import com.han.my_friend_kim_jung_san.databinding.ActivityCreateMeetingBinding
import com.han.my_friend_kim_jung_san.extensions.makeInVisible
import com.han.my_friend_kim_jung_san.extensions.makeVisible
import com.han.my_friend_kim_jung_san.ui.BaseActivity
import com.han.my_friend_kim_jung_san.ui.meeting.invite.RoomMemberRVAdapter

class CreateMeetingActivity: BaseActivity<ActivityCreateMeetingBinding>(ActivityCreateMeetingBinding::inflate), CreateMeetingRoomView {
    var stateColor: String? = null
    var users = ArrayList<User>()
    private var day: String? = null
    override fun initAfterBinding() {
        binding.dayTV.text = this.intent.getStringExtra("day")
        day = binding.dayTV.text.toString().replace(".", "-")
        //임시 유저
        users.add(User("김정산", null, 1))
        users.add(User("정산김", null, 1))
        users.add(User("산정김", null, 1))
        users.add(User("김산정", null, 1))
        users.add(User("동동동", null, 1))
        users.add(User("만만만", null, 1))
        users.add(User("지지지", null, 1))

        initRV()
        binding.backArrowIBtn.setOnClickListener {
            finish()
        }
        binding.stateRedIBtn.setOnClickListener {
            stateColor = "red"
            binding.stateColorIV.makeVisible()
            changeBackGroundColor(stateColor)
        }
        binding.stateOrangeIBtn.setOnClickListener {
            stateColor = "orange"
            binding.stateColorIV.makeVisible()
            changeBackGroundColor(stateColor)
        }
        binding.stateYellowIBtn.setOnClickListener {
            stateColor = "yellow"
            binding.stateColorIV.makeVisible()
            changeBackGroundColor(stateColor)
        }
        binding.stateGreenIBtn.setOnClickListener {
            stateColor = "green"
            binding.stateColorIV.makeVisible()
            changeBackGroundColor(stateColor)
        }
        binding.stateBlueIBtn.setOnClickListener {
            stateColor = "blue"
            binding.stateColorIV.makeVisible()
            changeBackGroundColor(stateColor)
        }
        binding.statePurpleIBtn.setOnClickListener {
            stateColor = "purple"
            binding.stateColorIV.makeVisible()
            changeBackGroundColor(stateColor)
        }


        binding.finishIBtn.setOnClickListener {
            createRoom()
        }

    }
    private fun initRV(){
        val roomMemberRVAdapter = RoomMemberRVAdapter(users)
        binding.inviteRV.adapter = roomMemberRVAdapter
        roomMemberRVAdapter.setItemClickListener(object : RoomMemberRVAdapter.UserRemoveClickListener{
            override fun onRemoveUser(position: Int) {
                roomMemberRVAdapter.removeItem(position)
            }
        })
        binding.inviteRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
    private fun changeBackGroundColor(color: String?){
        when(color){
            "red" -> {
                binding.stateColorIV.setImageResource(R.drawable.red)
                binding.stateRedIBtn.setBackgroundResource(R.drawable.selected_button)
                binding.stateOrangeIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateYellowIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateGreenIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateBlueIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.statePurpleIBtn.setBackgroundResource(R.drawable.unselected_button)
            }
            "orange" -> {
                binding.stateColorIV.setImageResource(R.drawable.orange)
                binding.stateRedIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateOrangeIBtn.setBackgroundResource(R.drawable.selected_button)
                binding.stateYellowIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateGreenIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateBlueIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.statePurpleIBtn.setBackgroundResource(R.drawable.unselected_button)
            }
            "yellow" -> {
                binding.stateColorIV.setImageResource(R.drawable.yellow)
                binding.stateRedIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateOrangeIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateYellowIBtn.setBackgroundResource(R.drawable.selected_button)
                binding.stateGreenIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateBlueIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.statePurpleIBtn.setBackgroundResource(R.drawable.unselected_button)
            }
            "green" -> {
                binding.stateColorIV.setImageResource(R.drawable.green)
                binding.stateRedIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateOrangeIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateYellowIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateGreenIBtn.setBackgroundResource(R.drawable.selected_button)
                binding.stateBlueIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.statePurpleIBtn.setBackgroundResource(R.drawable.unselected_button)
            }
            "blue" -> {
                binding.stateColorIV.setImageResource(R.drawable.blue)
                binding.stateRedIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateOrangeIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateYellowIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateGreenIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateBlueIBtn.setBackgroundResource(R.drawable.selected_button)
                binding.statePurpleIBtn.setBackgroundResource(R.drawable.unselected_button)
            }
            "purple" -> {
                binding.stateColorIV.setImageResource(R.drawable.purple)
                binding.stateRedIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateOrangeIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateYellowIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateGreenIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.stateBlueIBtn.setBackgroundResource(R.drawable.unselected_button)
                binding.statePurpleIBtn.setBackgroundResource(R.drawable.selected_button)
            }
        }
    }

    override fun onCreateSuccess() {
        showToast("모임방 생성 성공")
        finish()
    }

    override fun onCreateFailure(code: Int?, message: String?) {
        showToast("모임방 생성 실패패")
    }

    private fun createRoom(){
        if(binding.titleET.text.toString().isEmpty()){
            showToast("제목을 입력해 주세요!")
            return
        }
        if(stateColor == null){
            showToast("상태 색깔을 정해주세요!")
            return
        }
        if(binding.timeET.text.toString().isEmpty()){
            showToast("시작 시간을 입력해 주세요!")
            return
        }
        if(binding.inviteRV.isEmpty()){
            showToast("참가자 초대를 해주세요!")
            return
        }
        val title = binding.titleET.text.toString()
        val startTime = binding.timeET.text.toString()
        val room = Room(stateColor!!, listOf(123,234,345,456),title, day!!,startTime)

        RoomService.createRoom(this, room)
    }
}