package com.bliss.blissandroidchallenge.data.main.mapper

import com.bliss.blissandroidchallenge.data.main.model.DUserAvatar
import com.bliss.blissandroidchallenge.domain.model.UserAvatar
import com.bliss.blissandroidchallenge.domain.utils.BaseMapper

class UserAvatarMapper: BaseMapper<DUserAvatar, UserAvatar>() {
    override fun transform(entity: DUserAvatar): UserAvatar {
        return UserAvatar(
            login = entity.login,
            id = entity.id,
            avatarUrl = entity.avatarUrl
        )
    }
}