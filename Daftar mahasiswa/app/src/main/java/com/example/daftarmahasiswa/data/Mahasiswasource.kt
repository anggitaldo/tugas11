package com.example.daftarmahasiswa.data

import com.example.daftarmahasiswa.R
import com.example.daftarmahasiswa.model.Mahasiswa

class Mahasiswasource {
    fun loadMahasiswa() : List<Mahasiswa>{
        return listOf<Mahasiswa>(
            Mahasiswa(R.drawable.mhs1,R.string.mhs1),
            Mahasiswa(R.drawable.mhs2, R.string.mhs2),
            Mahasiswa(R.drawable.mhs3, R.string.mhs3),
            Mahasiswa(R.drawable.mhs4, R.string.mhs4),
            Mahasiswa(R.drawable.mhs5, R.string.mhs5)


        )
    }
}