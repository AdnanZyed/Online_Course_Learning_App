package com.example.onlinecourseampe_learningapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class My_View_Model extends AndroidViewModel {
    private LiveData<List<Notification>> allNotifications;

    private My_Repository repository;


    private final LiveData<List<Season_Reviews>> allReviews;
    private final LiveData<List<Expert_Reviews>> allReviewsT;


    public My_View_Model(@NonNull Application application) {
        super(application);
        repository = new My_Repository(application);
        allReviews = null;
        allReviewsT = null;
        allNotifications = repository.getAllNotifications();

    }

    public LiveData<List<Notification>> getAllNotifications() {
        return allNotifications;
    }

    public void addNotification(String title, String message, int iconResId) {
        repository.insert(new Notification(title, message, iconResId));
    }

    public void insertFarmerStep(FarmerStep farmerStep) {
        repository.insertFarmerStep(farmerStep);
    }

    public LiveData<List<FarmerStep>> getCompletedStepsForFarmer(String farmerUserName) {
        return repository.getCompletedStepsForFarmer(farmerUserName);
    }

    public void updateCompletionStatus(String farmerUserName, int stepId, boolean completed) {
        repository.updateCompletionStatus(farmerUserName, stepId, completed);
    }

    public void deleteFarmerStep(String farmerUserName, int stepId) {
        repository.deleteFarmerStep(farmerUserName, stepId);
    }

    public void insertMessage(Message message) {
        repository.insertMessage(message);
    }

    public LiveData<List<Message>> getMessagesBetweenUsers(String currentUser, String otherUser) {
        return repository.getMessagesBetweenUsers(currentUser, otherUser);
    }

    public LiveData<List<Farmer>> getAllFarmersExceptCurrent(String currentUsername) {
        return repository.getAllFarmersExceptCurrent(currentUsername);
    }

    public LiveData<Message> getLastMessageForUser(String username) {
        return repository.getLastMessageForUser(username);
    }
//

    public void insertSeasonStep(SeasonStep seasonStep) {
        repository.insertSeasonStep(seasonStep);
    }

    public LiveData<Integer> getTotalStepsCount() {
        return repository.getTotalStepsCount();
    }

    public LiveData<Integer> getCompletedStepsCount() {
        return repository.getCompletedStepsCount();
    }

    public LiveData<Integer> getTotalStepsTime() {
        return repository.getTotalStepsTime();
    }

    public void updateSeasonStep(SeasonStep seasonStep) {
        repository.updateSeasonStep(seasonStep);
    }

    public void deleteSeasonStep(SeasonStep seasonStep) {
        repository.deleteSeasonStep(seasonStep);
    }

    public LiveData<Integer> getTotalStepsCountBySeasonId(int seasonId) {
        return repository.getTotalStepsCountBySeasonId(seasonId);
    }

    public LiveData<Integer> getCompletedStepsCountBySeasonId(int seasonId) {
        return repository.getCompletedStepsCountBySeasonId(seasonId);
    }

    public LiveData<Integer> getTotalStepsTimeBySeasonId(int seasonId) {
        return repository.getTotalStepsTimeBySeasonId(seasonId);
    }

    public LiveData<List<SeasonStep>> getStepsBySeasonId(int seasonId) {
        return repository.getStepsBySeasonId(seasonId);
    }

    public void updateStepCompletionStatus(int stepId, boolean isCompleted) {
        repository.updateStepCompletionStatus(stepId, isCompleted);
    }

    public void insertReview(Season_Reviews review) {
        repository.insertReview(review);
    }

    public void deleteReviewByFarmer(String farmerUserName) {
        repository.deleteReviewByFarmer(farmerUserName);
    }

    public void updateReviewByFarmer(String farmerUserName, String newComment, float newRating) {
        repository.updateReviewByFarmer(farmerUserName, newComment, newRating);
    }

    public LiveData<List<Season_Reviews>> getAllReviewsBySeasonId(int seasonId) {
        return repository.getAllReviewsBySeasonId(seasonId);
    }


    public void insertReviewT(Expert_Reviews review) {
        repository.insertReviewT(review);
    }

    public void deleteReviewByFarmerT(String farmerUserName) {
        repository.deleteReviewByFarmerT(farmerUserName);
    }

    public void updateReviewByFarmerT(String farmerUserName, String newComment, float newRating) {
        repository.updateReviewByFarmerT(farmerUserName, newComment, newRating);
    }

    public LiveData<List<Expert_Reviews>> getAllReviewsBySeasonIdT(String expert) {
        return repository.getAllReviewsBySeasonIdT(expert);
    }


    void insertSeason(Season season) {
        repository.insertSeason(season);


    }

    void updateSeason(Season season) {
        repository.updateSeason(season);


    }

    void deleteSeason(Season season) {
        repository.deleteSeason(season);


    }


    LiveData<List<Season>> getAllSeason() {
        return repository.getAllSeason();
    }

    public LiveData<List<Season>> getBookmarkedSeasons() {
        return repository.getBookmarkedSeasons();
    }

    public LiveData<List<Farmer_Seasons>> getBookmarkedSeasonByFarmer(String farmerUsername) {
        return repository.getBookmarkedSeasonsByFarmer(farmerUsername);
    }

    public LiveData<List<Farmer_Seasons>> getisAddCartSeasonsByFarmer(String farmerUsername) {
        return repository.getisAddCartSeasonsByFarmer(farmerUsername);
    }

    public LiveData<List<Farmer_Seasons>> getBookmarkedSeasonsByFarmer1(String farmerUsername, int seasonId) {
        return repository.getBookmarkedSeasonsByFarmer1(farmerUsername, seasonId);

    }

    public LiveData<List<Farmer_Seasons>> getAddCartSeasonsByFarmer1(String farmerUsername, int seasonId) {
        return repository.getAddCartSeasonsByFarmer1(farmerUsername, seasonId);

    }

    public LiveData<List<Farmer_Seasons>> getisRatingSeasonsByFarmer1(String farmerUsername, int seasonId) {
        return repository.getisRatingSeasonsByFarmer1(farmerUsername, seasonId);
    }

    public LiveData<List<Farmer_Seasons>> getisRegisterSeasonsByFarmer1(String farmerUsername) {
        return repository.getisRegisterSeasonsByFarmer1(farmerUsername);
    }

    public LiveData<Void> deleteFarmerSeasonByUserAndSeason(String farmerUsername, int seasonId) {
        return repository.deleteFarmerSeasonByUserAndSeason(farmerUsername, seasonId);

    }   public LiveData<Void> delete1FarmerSeasonByUserAndSeason(String farmerUsername, int seasonId) {
        return repository.delete1FarmerSeasonByUserAndSeason(farmerUsername, seasonId);
    }

    public LiveData<List<Season>> updateBookmarkStatusAndGetSeasons(int seasonId, boolean isBookmarked) {
        return repository.updateBookmarkStatusAndGetSeasons(seasonId, isBookmarked);
    }

    public LiveData<List<Season>> updateisAddCartStatusAndGetSeasons(int seasonId, boolean isAddCart) {
        return repository.updateisAddCartStatusAndGetSeasons(seasonId, isAddCart);
    }


    public LiveData<List<Season>> getSeasonsByCategory(String category) {
        return repository.getSeasonsByCategory(category);
    }

    LiveData<List<Season>> getAllSeasonsById(int id) {
        return repository.getAllSeasonsById(id);
    }

    public LiveData<List<Season>> getAllSeasonsByIds(List<Integer> seasonIds) {
        return repository.getSesonsByIds(seasonIds);
    }

    LiveData<List<Season>> getAllseasonsByExpert_USER_Name(String Expert_USER_Name) {
        return repository.getAllSeasonsByExpert_USER_Name(Expert_USER_Name);
    }

    public LiveData<Boolean> isFarmerSeasonExists(String farmerUsername, int seasonId, boolean isRegister) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        My_Database.databaseWriteExecutor.execute(() -> {
            boolean exists = repository.isFarmerSeasonExists(farmerUsername, seasonId, isRegister);
            result.postValue(exists);
        });
        return result;
    }

    public LiveData<Boolean> isFarmerSeasonExistsC(String farmerUsername, int seasonId, boolean isAddCart) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        My_Database.databaseWriteExecutor.execute(() -> {
            boolean exists = repository.isFarmerSeasonExists1(farmerUsername, seasonId, isAddCart);
            result.postValue(exists);
        });
        return result;
    }

    public LiveData<Boolean> isFarmerSeasonExistsB(String farmerUsername, int seasonId, boolean isBookmark) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        My_Database.databaseWriteExecutor.execute(() -> {
            boolean exists = repository.isFarmerSeasonExistsB(farmerUsername, seasonId, isBookmark);
            result.postValue(exists);
        });
        return result;
    }

    public void insertFarmerSeason(Farmer_Seasons farmerSeason) {
        repository.insertFarmerSeason(farmerSeason);
    } public void deleteFarmerSeason(Farmer_Seasons farmerSeason) {
        repository.deleteFarmerSeason(farmerSeason);
    }

    public void updateSeasonFarmer(Farmer_Seasons farmerSeason) {
        repository.updateSeasonFarmer(farmerSeason);
    }

    public LiveData<List<Farmer_Seasons>> getSeasonsByFarmer(String farmerUsername) {
        return repository.getSeasonsByFarmer(farmerUsername);
    }

    public LiveData<List<Farmer_Seasons>> getFarmersBySeason(int seasonId) {
        return repository.getFarmersBySeason(seasonId);
    }

    public LiveData<List<Farmer_Seasons>> getFarmersBySeasonAndFarmer(String user, int seasonId) {
        return repository.getFarmersBySeasonAndFarmer(user, seasonId);
    }

    void insertFarmer(Farmer farmer) {

        repository.insertFarmer(farmer);


    }

    void updateFarmer(Farmer farmer) {
        repository.updateFarmer(farmer);


    }

    void deleteFarmer(Farmer farmer) {
        repository.deleteFarmer(farmer);


    }

    LiveData<List<Farmer>> getAllFarmer() {

        return repository.getAllFarmer();
    }

    LiveData<List<Farmer>> getFarmerByUsernameAndPassword(String username, String password) {
        return repository.getFarmerByUsernameAndPassword(username, password);
    }

    LiveData<List<Farmer>> getAllFarmerByUser(String farmer_user_name) {

        return repository.getAllFarmerByUser(farmer_user_name);
    }


    void insertExpert(Expert expert) {

        repository.insertExpert(expert);


    }

    void updateExpert(Expert expert) {
        repository.updateExpert(expert);


    }

    void deleteExpert(Expert expert) {
        repository.deleteExpert(expert);


    }

    LiveData<List<Expert>> getAllExpert() {

        return repository.getAllExpert();
    }


    public LiveData<List<Expert>> searchExperts(String expertName) {
        return repository.searchExperts(expertName);
    }

    public LiveData<List<Season>> searchSeasons(String seasonName) {
        return repository.searchSeasons(seasonName);
    }

    LiveData<List<Expert>> getAllExpertByUser(String Teatur_USER_Name) {

        return repository.getAllExpertByUser(Teatur_USER_Name);
    }

    public void insertFarmerExpert(Farmer_Expert farmerExpert) {
        repository.insertFarmerExpert(farmerExpert);
    }

    public LiveData<List<Farmer_Expert>> getExpertsByFarmer(String farmerUsername) {
        return repository.getExpertsByFarmer(farmerUsername);
    }

    public LiveData<List<Farmer_Expert>> getFarmersByExpert(String expertUsername) {
        return repository.getFarmersByExpert(expertUsername);
    }

}