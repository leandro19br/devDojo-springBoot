package br.com.devdojo.error;

/*classe para criação de customização de retorno de erro da API*/
public class ResourceNotFoundDetails extends ErrorDetails {

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private long timesTamp;
        private String developerMessage;

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

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setStatus(status);
            resourceNotFoundDetails.setDetail(detail);
            resourceNotFoundDetails.setDeveloperMessage(developerMessage);
            resourceNotFoundDetails.setTitle(title);
            resourceNotFoundDetails.setTimesTamp(timesTamp);
            return resourceNotFoundDetails;
        }
    }
}
