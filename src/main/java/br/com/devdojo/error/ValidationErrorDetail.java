package br.com.devdojo.error;

public class ValidationErrorDetail extends ErrorDetails {

    private String field;
    private String fieldMessage;

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private long timesTamp;
        private String developerMessage;
        private String field;
        private String fieldMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timesTamp(long timesTamp) {
            this.timesTamp = timesTamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder fieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationErrorDetail build() {
            ValidationErrorDetail validationErrorDetail = new ValidationErrorDetail();
            validationErrorDetail.setStatus(status);
            validationErrorDetail.setDetail(detail);
            validationErrorDetail.setDeveloperMessage(developerMessage);
            validationErrorDetail.setTitle(title);
            validationErrorDetail.setTimesTamp(timesTamp);
            validationErrorDetail.field = this.field;
            validationErrorDetail.fieldMessage = this.fieldMessage;
            return validationErrorDetail;
        }
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }
}