package com.nms.tourleader.repository

import com.nms.tourleader.entity.Group
import org.springframework.data.jpa.repository.JpaRepository

interface GroupRepository: JpaRepository<Group, String>