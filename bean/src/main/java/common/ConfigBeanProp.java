package common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config")
@PropertySource(value = "config.properties")
public class ConfigBeanProp {

    private String file_url;
    private String file_upload_folder;


    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getFile_upload_folder() {
        return file_upload_folder;
    }

    public void setFile_upload_folder(String file_upload_folder) {
        this.file_upload_folder = file_upload_folder;
    }
}
