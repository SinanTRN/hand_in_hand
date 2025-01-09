package com.example.hand_in_hand.service.contracts;

import com.example.hand_in_hand.entities.models.Demand;
import com.example.hand_in_hand.entities.models.VolunteerActivities;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VolunteerActivitesService {
    VolunteerActivities save(VolunteerActivities entity);
    List<VolunteerActivities> findAll();
    VolunteerActivities findById(int id);
    void update(VolunteerActivities entity);
    void deleteFindId(int id);
    List<VolunteerActivities> getVolunteerActivitiesBystatus(int status);

//Görünüşte CategoryService ve CategoryDAO  benzer CRUD (Create, Read, Update, Delete) işlemleri yapılabilir gibi görünüyor,
// ancak Service ve DAO katmanlarının işlevleri farklıdır.
//CategoryService, iş mantığını yürütmek için CategoryDAOyu kullanır; yani veritabanına doğrudan erişim sağlamaz, yalnızca CategoryDAO üzerinden işlem yapar.
//DAO (Data Access Object) Katmanı: Bu katmanın tek görevi, veri tabanı ile ilgili işlemleri yönetmektir.
// CategoryDAO veya CategoryDAOImpl, doğrudan veri tabanı bağlantısı kurar ve SQL işlemlerini burada gerçekleştirir.Bu sınıf,CRUD işlemlerini gerçekleştirir
//Service Katmanı: CategoryService sınıfı iş mantığını barındırır ve veri erişim işlemleri için DAO katmanına bağımlıdır.
// Bu katman, kullanıcıdan gelen isteklere göre kararlar alır, çeşitli iş kuralları uygular ve veri erişim katmanına erişir.
//Neden İkisi de CRUD İşlemlerini İçeriyor gibi Görünüyor?:
//Service katmanındaki CRUD işlemleri, yalnızca iş mantığı açısından CRUD işlemleri olarak adlandırılabilir.
// Örneğin, bir kategori eklemek (save) gibi bir işlem, iş mantığı açısından anlamlıdır, ama veritabanına nasıl kaydedileceğini Service değil DAO katmanı belirler.
//Service katmanındaki kod, DAO katmanını kullanarak CRUD işlemlerini gerçekleştirir.
//ÖRNEK;DAOdaki save` metodu, doğrudan veri tabanında bir satır ekler veya günceller.
//Service'deki save metodu, DAO’daki save metodunu çağırarak iş mantığına göre belirli kontroller yapabilir, örneğin:
//Kategori adının benzersiz olup olmadığını kontrol edebilir.
//Kullanıcının bu işlemi yapmaya yetkisi olup olmadığını kontrol edebilir.
//Kategori ekleme işlemi başarılıysa başka bir işlemi tetikleyebilir.
}
