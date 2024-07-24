//package com.example.Brewplan.Service;
//
//import com.example.Brewplan.Model.DailyStock;
//import com.example.Brewplan.Repository.DailyStockRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class DailyStockService {
//
//    @Autowired
//    private DailyStockRepository dailyStockRepository;
//
//    public List<DailyStock> getAllDailyStocks() {
//        return dailyStockRepository.findAll();
//    }
//
//    public Optional<DailyStock> getDailyStockById(Long id) {
//        return dailyStockRepository.findById(id);
//    }
//
//    public DailyStock saveDailyStock(DailyStock dailyStock) {
//        return dailyStockRepository.save(dailyStock);
//    }
//
//    public void deleteDailyStock(Long id) {
//        dailyStockRepository.deleteById(id);
//    }
//
//    public List<DailyStock> searchByProductName(String query) {
//        return dailyStockRepository.findByProductNameContainingIgnoreCase(query);
//    }
//}
