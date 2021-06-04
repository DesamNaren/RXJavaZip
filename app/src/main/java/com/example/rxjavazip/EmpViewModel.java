package com.example.rxjavazip;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class EmpViewModel extends ViewModel {

    LiveData<List<String>> getData(EmpRepository empRepository) {
        EmpService empService = EmpService.Factory.create();
        return empRepository.getData(empService);
    }

}
