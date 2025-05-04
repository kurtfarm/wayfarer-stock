package com.dkprint.app.copperplate.domain.entity

import com.dkprint.app.core.infrastructure.jpa.shared.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "copperplate_history")
class CopperplateHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @JoinColumn(name = "copperplate_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    val copperPlate: Copperplate,

    @Column(name = "history_date", nullable = false)
    val historyDate: LocalDate,

    @Column(name = "content", nullable = false)
    val content: String,

    @Column(name = "vendor_id", nullable = false)
    var vendorId: Long,

    @Column(name = "comment", length = 100)
    val comment: String?,
): BaseEntity() {
}
