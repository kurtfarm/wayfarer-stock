package com.dkprint.app.copperplate.domain.repository

import com.dkprint.app.copperplate.domain.entity.CopperplateHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CopperplateHistoryRepository : JpaRepository<CopperplateHistory, Long>
