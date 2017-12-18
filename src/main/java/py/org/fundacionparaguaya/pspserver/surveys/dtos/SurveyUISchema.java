package py.org.fundacionparaguaya.pspserver.surveys.dtos;
/*
 * FP-PSP Server
 * A sample API to manage surveys
 *
 * OpenAPI spec version: 1.0.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The UI SCHEMA definition of the survey. Holds info clients would use to render the survey
 */
@ApiModel(description = "The UI SCHEMA definition of the survey. Holds info clients would use to render the survey")
public class SurveyUISchema implements Serializable {


    public static final String UI_GROUP_ECONOMICS = "ui:group:economics";
    public static final String UI_GROUP_INDICTATORS = "ui:group:indicators";

    @JsonProperty("properties")
    private Map<String, Property> properties = new HashMap<>();

    @JsonProperty("ui:order")
    private List<String> uiOrder = null;

    @JsonProperty("ui:group:personal")
    private List<String> groupPersonal = null;

    @JsonProperty("ui:group:economics")
    private List<String> groupEconomics = null;

    @JsonProperty("ui:group:indicators")
    private List<String> groupIndicators = null;

    @JsonProperty("ui:custom:fields")
    private Map<String, Object> customFields = null;

    public SurveyUISchema() {}

    public SurveyUISchema properties(Map<String, Property> properties) {
        this.properties = properties;
        return this;
    }


    @ApiModelProperty("Specifies the order in which the fields should be rendererd in the UI")
    public List<String> getUiOrder() {
        return uiOrder;
    }

    public void setUiOrder(List<String> uiOrder) {
        this.uiOrder = uiOrder;
    }

    @ApiModelProperty("Specifies which fields should be grouped as personal-information in the UI")
    public List<String> getGroupPersonal() {
        return groupPersonal;
    }

    public void setGroupPersonal(List<String> groupPersonal) {
        this.groupPersonal = groupPersonal;
    }

    @ApiModelProperty("Specifies which fields should be grouped as socio-economics in the UI")
    public List<String> getGroupEconomics() {
        return groupEconomics;
    }

    public void setGroupEconomics(List<String> groupEconomics) {
        this.groupEconomics = groupEconomics;
    }

    @ApiModelProperty("Specifies which fields should be grouped as indicators in the UI")
    public List<String> getGroupIndicators() {
        return groupIndicators;
    }

    public void setGroupIndicators(List<String> groupIndicators) {
        this.groupIndicators = groupIndicators;
    }

    @ApiModelProperty("Specifies which fields were custom")
    public Map<String, Object> getCustomFields() {
        return this.customFields;
    }

    public void setCustomFields(Map<String, Object> customFields) {
        this.customFields = customFields;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SurveyUISchema {\n");
        sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
        sb.append("    groupPersonal: ").append(toIndentedString(groupPersonal)).append("\n");
        sb.append("    groupEconomics: ").append(toIndentedString(groupEconomics)).append("\n");
        sb.append("    groupIndicators: ").append(toIndentedString(groupIndicators)).append("\n");
        sb.append("    customfields: ").append(toIndentedString(customFields)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyUISchema that = (SurveyUISchema) o;

        return Objects.equal(this.properties, that.properties) &&
                Objects.equal(this.uiOrder, that.uiOrder) &&
                Objects.equal(this.groupPersonal, that.groupPersonal) &&
                Objects.equal(this.groupEconomics, that.groupEconomics) &&
                Objects.equal(this.groupIndicators, that.groupIndicators) && 
                Objects.equal(this.customFields, that.customFields);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(properties, uiOrder, groupPersonal, groupEconomics, groupIndicators, customFields);
    }


}

