package com.andreibacalu.android.complesportiv.logic.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AvailableSlotsResponse {
    public String message;
    @SerializedName("available_slots")
    public List<AvailableSlot> availableSlots;
    public int success;
    @SerializedName("service_details")
    public String serviceDetails;

    public class AvailableSlot {
        @SerializedName("is_available")
        public int isAvailable;
        public long time;
        @SerializedName("group_id")
        public String groupId;
        @SerializedName("staff_id")
        public String staffId;
    }
}
