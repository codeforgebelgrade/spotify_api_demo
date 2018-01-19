package com.codeforge.demo.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.List;

public class ShellEventModel extends WebmotifShellObject implements Serializable {

    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Event {

        private String code;
        private String market;
        private String language;
        private String timezone;
        private String eventId;
        private String eventName;
        private String creationDateTime;
        private String eventDateTime;
        private String onsaleDateTime;
        private String venueId;
        private List<Integer> categoryIds;
        private String url;
        private List<Integer> attractionIds;
        private List<SubChannel> subChannels;
        private Boolean dateTimeHidden;
        private Boolean timeHidden;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMarket() {
            return market;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public String getEventDateTime() {
            return eventDateTime;
        }

        public void setEventDateTime(String eventDateTime) {
            this.eventDateTime = eventDateTime;
        }

        public String getOnsaleDateTime() {
            return onsaleDateTime;
        }

        public void setOnsaleDateTime(String onsaleDateTime) {
            this.onsaleDateTime = onsaleDateTime;
        }

        public String getVenueId() {
            return venueId;
        }

        public void setVenueId(String venueId) {
            this.venueId = venueId;
        }

        public List<Integer> getCategoryIds() {
            return categoryIds;
        }

        public void setCategoryIds(List<Integer> categoryIds) {
            this.categoryIds = categoryIds;
        }

        @JsonProperty("URL")
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<Integer> getAttractionIds() {
            return attractionIds;
        }

        public void setAttractionIds(List<Integer> attractionIds) {
            this.attractionIds = attractionIds;
        }

        public List<SubChannel> getSubChannels() {
            return subChannels;
        }

        public void setSubChannels(List<SubChannel> subChannels) {
            this.subChannels = subChannels;
        }

        public String getCreationDateTime() {
            return creationDateTime;
        }

        public void setCreationDateTime(String creationDateTime) {
            this.creationDateTime = creationDateTime;
        }

        public Boolean getDateTimeHidden() {
            return dateTimeHidden;
        }

        public void setDateTimeHidden(Boolean dateTimeHidden) {
            this.dateTimeHidden = dateTimeHidden;
        }

        public Boolean getTimeHidden() {
            return timeHidden;
        }

        public void setTimeHidden(Boolean timeHidden) {
            this.timeHidden = timeHidden;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
        public static class SubChannel {

            private Integer subChannelId;
            private String onsaleDateTime;
            private Visibility visibility;

            public Integer getSubChannelId() {
                return subChannelId;
            }

            public void setSubChannelId(Integer subChannelId) {
                this.subChannelId = subChannelId;
            }

            public String getOnsaleDateTime() {
                return onsaleDateTime;
            }

            public void setOnsaleDateTime(String onsaleDateTime) {
                this.onsaleDateTime = onsaleDateTime;
            }

            public Visibility getVisibility() {
                return visibility;
            }

            public void setVisibility(Visibility visibility) {
                this.visibility = visibility;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
            public static class Visibility {

                private String defaultStatus;

                private String exceptionStatus;

                private String exceptionStartDate;

                public String getDefaultStatus() {
                    return defaultStatus;
                }

                public void setDefaultStatus(String defaultStatus) {
                    this.defaultStatus = defaultStatus;
                }

                public String getExceptionStatus() {
                    return exceptionStatus;
                }

                public void setExceptionStatus(String exceptionStatus) {
                    this.exceptionStatus = exceptionStatus;
                }

                public String getExceptionStartDate() {
                    return exceptionStartDate;
                }

                public void setExceptionStartDate(String exceptionStartDate) {
                    this.exceptionStartDate = exceptionStartDate;
                }
            }
        }

    }

}
