package com.example.onlinecourseampe_learningapp;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class My_Repository {

    private FarmerStepDao farmerStepDao;

    private Farmer_Season_Dao farmerSeasonDao;
    private Farmer_Expert_Dao farmerExpertDao;
    private Expert_Dao expertDao;
    private Farmer_Dao farmerDao;
    private Season_Dao seasonDao;
    private SeasonReviewsDao seasonReviewsDao;
    private ExpertReviewsDao expertReviewsDao;
    private SeasonStepsDao seasonStepsDao;
    private final NotificationDao notificationDao;
    private final LiveData<List<Notification>> allNotifications;
    private final MessageDao messageDao;
    private final ExecutorService executorService;

    private LiveData<List<Expert>> AllExpert;
    private LiveData<List<Farmer>> AllFarmers;
    private LiveData<List<Season>> AllSeason;


    My_Repository(Application application) {
        My_Database db = My_Database.getDatabase(application);
        farmerDao = db.farmerDao();
        seasonDao = db.seasonDao();
        expertDao = db.expertDao();
        seasonReviewsDao = db.seasonReviewsDao();
        expertReviewsDao = db.expertReviewsDao();
        farmerSeasonDao = db.farmerSeasonDao();
        farmerExpertDao = db.farmerExpertDao();
        seasonStepsDao = db.seasonStepsDao();
        messageDao = db.messageDao();
        executorService = Executors.newSingleThreadExecutor();
        this.farmerStepDao = db.farmerStepDao();
        notificationDao = db.notificationDao();
        allNotifications = notificationDao.getAllNotifications();


    }

    public LiveData<List<Notification>> getAllNotifications() {
        return allNotifications;
    }

    public void insert(Notification notification) {
        executorService.execute(() -> notificationDao.insert(notification));
    }

    public void insertFarmerStep(FarmerStep farmerStep) {
        My_Database.databaseWriteExecutor.execute(() -> {
            farmerStepDao.insertFarmerStep(farmerStep);
        });
    }

    public void deleteFarmerStep(String farmerUserName, int stepId) {
        My_Database.databaseWriteExecutor.execute(() -> {
            farmerStepDao.deleteFarmerStep(farmerUserName, stepId);
        });
    }

    public void updateCompletionStatus(String farmerUserName, int stepId, boolean completed) {
        My_Database.databaseWriteExecutor.execute(() -> {
            farmerStepDao.updateCompletionStatus(farmerUserName, stepId, completed);
        });
    }

    public LiveData<List<FarmerStep>> getCompletedStepsForFarmer(String farmerUserName) {
        return farmerStepDao.getCompletedStepsForFarmer(farmerUserName);
    }


    public void insertMessage(Message message) {
        My_Database.databaseWriteExecutor.execute(() -> messageDao.insertMessage(message));
    }

    public LiveData<List<Message>> getMessagesBetweenUsers(String currentUser, String otherUser) {
        return messageDao.getMessagesBetweenUsers(currentUser, otherUser);
    }

    public LiveData<Message> getLastMessageForUser(String username) {
        return messageDao.getLastMessageForUser(username);
    }

    public LiveData<List<Farmer>> getAllFarmersExceptCurrent(String currentUsername) {
        return farmerDao.getAllFarmersExcept(currentUsername);
    }

    public LiveData<Integer> getTotalStepsCountBySeasonId(int seasonId) {
        MutableLiveData<Integer> result = new MutableLiveData<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            int count = seasonStepsDao.getTotalStepsCountBySeasonId(seasonId);
            result.postValue(count);
        });
        return result;
    }

    public LiveData<Integer> getCompletedStepsCountBySeasonId(int seasonId) {
        MutableLiveData<Integer> result = new MutableLiveData<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            int count = seasonStepsDao.getCompletedStepsCountBySeasonId(seasonId);
            result.postValue(count);
        });
        return result;
    }

    public LiveData<Integer> getTotalStepsTimeBySeasonId(int seasonId) {
        MutableLiveData<Integer> result = new MutableLiveData<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            int totalTime = seasonStepsDao.getTotalStepsTimeBySeasonId(seasonId);
            result.postValue(totalTime);
        });
        return result;
    }

    public void insertSeasonStep(SeasonStep seasonStep) {
        My_Database.databaseWriteExecutor.execute(() -> {

            seasonStepsDao.insert(seasonStep);

        });

    }


    public void updateSeasonStep(SeasonStep seasonStep) {
        My_Database.databaseWriteExecutor.execute(() -> {
            seasonStepsDao.update(seasonStep);

        });


    }

    public LiveData<Integer> getTotalStepsCount() {
        MutableLiveData<Integer> totalStepsCount = new MutableLiveData<>();
        My_Database.databaseWriteExecutor.execute(() -> {
            totalStepsCount.postValue(seasonStepsDao.getTotalStepsCount());
        });
        return totalStepsCount;
    }

    public LiveData<Integer> getCompletedStepsCount() {
        MutableLiveData<Integer> completedStepsCount = new MutableLiveData<>();
        My_Database.databaseWriteExecutor.execute(() -> {
            completedStepsCount.postValue(seasonStepsDao.getCompletedStepsCount());
        });
        return completedStepsCount;
    }

    public LiveData<Integer> getTotalStepsTime() {
        MutableLiveData<Integer> totalStepsTime = new MutableLiveData<>();
        My_Database.databaseWriteExecutor.execute(() -> {
            totalStepsTime.postValue(seasonStepsDao.getTotalStepsTime());
        });
        return totalStepsTime;
    }


    public void deleteSeasonStep(SeasonStep seasonStep) {
        My_Database.databaseWriteExecutor.execute(() -> {

            seasonStepsDao.delete(seasonStep);
        });
    }

    public LiveData<List<SeasonStep>> getStepsBySeasonId(int seasonId) {
        return seasonStepsDao.getStepsBySeasonId(seasonId);
    }

    public void updateStepCompletionStatus(int stepId, boolean isCompleted) {
        My_Database.databaseWriteExecutor.execute(() -> {

            seasonStepsDao.updateStepCompletionStatus(stepId, isCompleted);
        });

    }

    void insertReview(Season_Reviews review) {
        My_Database.databaseWriteExecutor.execute(() -> {
            seasonReviewsDao.insertReview(review);
        });
    }

    void updateReviewByFarmer(String farmerUserName, String newComment, float newRating) {
        My_Database.databaseWriteExecutor.execute(() -> {
            seasonReviewsDao.updateReviewByFarmer(farmerUserName, newComment, newRating);
        });
    }

    void deleteReviewByFarmer(String farmerUserName) {
        My_Database.databaseWriteExecutor.execute(() -> {

            seasonReviewsDao.deleteReviewByFarmer(farmerUserName);
        });
    }

    public LiveData<List<Season_Reviews>> getAllReviewsBySeasonId(int seasonId) {
        return seasonReviewsDao.getAllReviewsBySeasonId(seasonId);
    }


    void insertReviewT(Expert_Reviews review) {
        My_Database.databaseWriteExecutor.execute(() -> {
            expertReviewsDao.insertReviewT(review);
        });
    }


    void updateReviewByFarmerT(String farmerUserName, String newComment, float newRating) {
        My_Database.databaseWriteExecutor.execute(() -> {
            expertReviewsDao.updateReviewByFarmerT(farmerUserName, newComment, newRating);
        });
    }

    void deleteReviewByFarmerT(String farmerUserName) {
        My_Database.databaseWriteExecutor.execute(() -> {

            expertReviewsDao.deleteReviewByFarmerT(farmerUserName);
        });
    }

    public LiveData<List<Expert_Reviews>> getAllReviewsBySeasonIdT(String expert) {
        return expertReviewsDao.getAllReviewsBySeasonIdT(expert);
    }

    void updateSeason(Season season) {
        My_Database.databaseWriteExecutor.execute(() -> {
            seasonDao.updateSeason(season);


        });


    }

    void insertSeason(Season season) {
        My_Database.databaseWriteExecutor.execute(() -> {
            seasonDao.insertSeason(season);


        });

    }


    void deleteSeason(Season season) {
        My_Database.databaseWriteExecutor.execute(() -> {
            seasonDao.deleteSeason(season);


        });

    }

    public LiveData<List<Season>> getBookmarkedSeasons() {
        return seasonDao.getBookmarkedSeasons();
    }

    public LiveData<List<Farmer_Seasons>> getBookmarkedSeasonsByFarmer(String farmerUsername) {
        return farmerSeasonDao.getBookmarkedSeasonsByFarmer(farmerUsername);
    }

    public LiveData<List<Farmer_Seasons>> getBookmarkedSeasonsByFarmer1(String farmerUsername, int seasonId) {
        return farmerSeasonDao.getBookmarkedSeasonsByFarmer1(farmerUsername, seasonId);

    }

    public LiveData<List<Farmer_Seasons>> getAddCartSeasonsByFarmer1(String farmerUsername, int seasonId) {
        return farmerSeasonDao.getisAddCartSeasonsByFarmer1(farmerUsername, seasonId);
    }

    public LiveData<List<Farmer_Seasons>> getisRatingSeasonsByFarmer1(String farmerUsername, int seasonId) {
        return farmerSeasonDao.getisRatingSeasonsByFarmer1(farmerUsername, seasonId);
    }

    public LiveData<List<Farmer_Seasons>> getisRegisterSeasonsByFarmer1(String farmerUsername) {
        return farmerSeasonDao.getisRegisterSeasonsByFarmer1(farmerUsername);
    }

    public LiveData<List<Farmer_Seasons>> getisAddCartSeasonsByFarmer(String farmerUsername) {
        return farmerSeasonDao.getisAddCartSeasonsByFarmer(farmerUsername);
    }

    public LiveData<List<Season>> updateBookmarkStatusAndGetSeasons(int seasonId, boolean isBookmarked) {
        seasonDao.updateBookmarkStatus(seasonId, isBookmarked);
        return seasonDao.getAllSeason();
    }

    public LiveData<List<Season>> updateisAddCartStatusAndGetSeasons(int seasonId, boolean isAddCart) {
        seasonDao.updateBookmarkStatus(seasonId, isAddCart);
        return seasonDao.getAllSeason();
    }


    LiveData<List<Season>> getAllSeason() {
        return seasonDao.getAllSeason();
    }

    LiveData<List<Season>> getAllSeasonsById(int id) {


        return seasonDao.getAllSeasonsById(id);
    }

    LiveData<List<Season>> getAllSeasonsByExpert_USER_Name(String Expert_USER_Name) {


        return seasonDao.getAllSeasonsByExpert_USER_Name(Expert_USER_Name);
    }


    public LiveData<List<Season>> getSeasonsByCategory(String category) {
        return seasonDao.getSeasonByCategory(category);
    }

    public boolean isFarmerSeasonExists(String farmerUsername, int seasonId, boolean isRegister) {
        return farmerSeasonDao.isFarmerSeasonExists(farmerUsername, seasonId, isRegister) > 0;
    }

    public boolean isFarmerSeasonExists1(String farmerUsername, int seasonId, boolean isAddCart) {
        return farmerSeasonDao.isFarmerSeasonExists1(farmerUsername, seasonId, isAddCart) > 0;
    }

    public boolean isFarmerSeasonExistsB(String farmerUsername, int seasonId, boolean isBookmark) {
        return farmerSeasonDao.isFarmerSeasonExistsB(farmerUsername, seasonId, isBookmark) > 0;
    }

    public void insertFarmerSeason(Farmer_Seasons farmerSeason) {
        My_Database.databaseWriteExecutor.execute(() -> {
            farmerSeasonDao.insertFarmerSeason(farmerSeason);
        });
    } public void deleteFarmerSeason(Farmer_Seasons farmerSeason) {
        My_Database.databaseWriteExecutor.execute(() -> {
            farmerSeasonDao.deleteFarmerSeason(farmerSeason);
        });
    }

    public void updateSeasonFarmer(Farmer_Seasons farmerSeason) {
        My_Database.databaseWriteExecutor.execute(() -> {
            farmerSeasonDao.updateSeasonFarmer(farmerSeason);
        });
    }

    public LiveData<Void> deleteFarmerSeasonByUserAndSeason(String farmerUsername, int seasonId) {
        MutableLiveData<Void> result = new MutableLiveData<>();

        executorService.execute(() -> {
            farmerSeasonDao.deleteFarmerSeasonByUserAndSeason(farmerUsername, seasonId);
            result.postValue(null);
        });
        return result;

    }    public LiveData<Void> delete1FarmerSeasonByUserAndSeason(String farmerUsername, int seasonId) {
        MutableLiveData<Void> result = new MutableLiveData<>();

        executorService.execute(() -> {
            farmerSeasonDao.delete1FarmerSeasonByUserAndSeason(farmerUsername, seasonId);
            result.postValue(null);
        });
        return result;

    }

    public LiveData<List<Farmer_Seasons>> getSeasonsByFarmer(String farmerUsername) {
        return farmerSeasonDao.getSeasonsByFarmer(farmerUsername);
    }

    public LiveData<List<Farmer_Seasons>> getFarmersBySeason(int seasonId) {
        return farmerSeasonDao.getFarmersBySeason(seasonId);
    }

    public LiveData<List<Farmer_Seasons>> getFarmersBySeasonAndFarmer(String user, int seasonId) {
        return farmerSeasonDao.getFarmersBySeasonAndFarmer(user, seasonId);
    }

    public LiveData<List<Season>> getSesonsByIds(List<Integer> seasonIds) {
        return seasonDao.getSeasonsByIds(seasonIds);
    }

    void insertFarmer(Farmer farmer) {
        My_Database.databaseWriteExecutor.execute(() -> {

            farmerDao.insertFarmer(farmer);


        });
    }

    void updateFarmer(Farmer farmer) {
        My_Database.databaseWriteExecutor.execute(() -> {
            farmerDao.updateFarmer(farmer);


        });
    }

    void deleteFarmer(Farmer farmer) {
        My_Database.databaseWriteExecutor.execute(() -> {
            farmerDao.deleteFarmer(farmer);


        });
    }

    LiveData<List<Farmer>> getAllFarmer() {

        return farmerDao.getAllFarmers();
    }

    LiveData<List<Farmer>> getFarmerByUsernameAndPassword(String username, String password) {
        return farmerDao.getFarmerByUsernameAndPassword(username, password);
    }

    LiveData<List<Farmer>> getAllFarmerByUser(String farmer_user_name) {

        return farmerDao.getAllFarmersByUser(farmer_user_name);
    }


    void insertExpert(Expert expert) {
        My_Database.databaseWriteExecutor.execute(() -> {

            expertDao.insertExpert(expert);


        });
    }

    void updateExpert(Expert expert) {
        My_Database.databaseWriteExecutor.execute(() -> {
            expertDao.updateExpert(expert);


        });
    }

    void deleteExpert(Expert expert) {
        My_Database.databaseWriteExecutor.execute(() -> {
            expertDao.deleteExpert(expert);


        });
    }

    LiveData<List<Expert>> getAllExpert() {

        return expertDao.getAllExperts();
    }

    public LiveData<List<Expert>> searchExperts(String expertName) {
        return expertDao.getExpertByName("%" + expertName + "%");
    }

    public LiveData<List<Season>> searchSeasons(String seasonName) {
        return seasonDao.getSeasonName("%" + seasonName + "%");
    }

    LiveData<List<Expert>> getAllExpertByUser(String Teatur_USER_Name) {

        return expertDao.getAllExpertsByUser(Teatur_USER_Name);
    }

    public void insertFarmerExpert(Farmer_Expert farmerExpert) {
        My_Database.databaseWriteExecutor.execute(() -> {
            farmerExpertDao.insertFarmerExpert(farmerExpert);
        });
    }

    public LiveData<List<Farmer_Expert>> getExpertsByFarmer(String farmerUsername) {
        return farmerExpertDao.getExpertsByFarmer(farmerUsername);
    }

    public LiveData<List<Farmer_Expert>> getFarmersByExpert(String expertUsername) {
        return farmerExpertDao.getFarmersByExpert(expertUsername);
    }

}
