package com.example.baeminclone.database.dao

import androidx.room.*
import com.example.baeminclone.database.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {

    /** 주소 등록 **/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAddress(addressEntity: AddressEntity) : Long

    /** 등록된 주소 조회 **/
    @Query("SELECT * FROM AddressEntity")
    fun selectAllAddress() : Flow<List<AddressEntity>>

    /** 주소 등록 시 모든 상태값 변경 **/
    @Query("UPDATE AddressEntity SET status = 0 WHERE id != (SELECT id FROM AddressEntity ORDER BY id DESC LIMIT 1)")
    suspend fun updateAllStatusFromInsert() : Int

    /** 특정 주소 제외 상태값 변경 **/
    @Query("UPDATE AddressEntity SET status = 0 WHERE id != :id")
    suspend fun updateAllStatusFromSelect(id : Long) : Int

    /** 특정 주소 상태값 변경 **/
    @Query("UPDATE AddressEntity SET status = 1 WHERE id = :id")
    suspend fun updateStatus(id: Long) : Int

    @Transaction
    suspend fun updateStatusFromSelect(id: Long) {
        updateAllStatusFromSelect(id)
        updateStatus(id)
    }

}