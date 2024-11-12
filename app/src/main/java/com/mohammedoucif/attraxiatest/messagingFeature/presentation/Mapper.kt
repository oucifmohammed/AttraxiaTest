package com.mohammedoucif.attraxiatest.messagingFeature.presentation

import com.mohammedoucif.attraxiatest.designSystem.component.messageItem.model.MessageSide
import com.mohammedoucif.attraxiatest.designSystem.component.messageItem.model.MessageUIModel
import com.mohammedoucif.attraxiatest.fetchMessagesComponent.domain.entity.Message
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

const val dateFormat = "dd/MM/yyyy HH:mm"
val calendar: Calendar = Calendar.getInstance()

fun Message.toMessageUI(): MessageUIModel = MessageUIModel(
    id = id,
    senderId = senderId,
    receiverId = receiverId,
    content = content,
    isSeen = isSeen,
    messageSide = if(currentUserId == senderId) MessageSide.END else MessageSide.START,
    timeSent = with(calendar) {
        timeInMillis = timeSent
        SimpleDateFormat(dateFormat, Locale.getDefault()).format(time)
    }
)