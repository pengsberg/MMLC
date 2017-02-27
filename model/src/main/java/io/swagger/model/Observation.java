/*
 * API for memy.life
 * An API for accessing memy.life
 *
 * OpenAPI spec version: 1.0.5
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Location;
import io.swagger.model.Observationvaluetype;
import java.util.ArrayList;
import java.util.List;

/**
 * Observation
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-09T12:54:48.614Z")
public class Observation extends Entity {
  @JsonProperty("docid")
  private String docid = null;

  @JsonProperty("systemid")
  private String systemid = null;

  @JsonProperty("userid")
  private String userid = null;

  @JsonProperty("subjectid")
  private String subjectid = null;

  @JsonProperty("observationtype")
  private String observationtype = null;

  @JsonProperty("observationcreateddatetime")
  private String observationcreateddatetime = null;

  @JsonProperty("location")
  private Location location = null;

  @JsonProperty("valuecomponents")
  private List<Observationvaluetype> valuecomponents = new ArrayList<Observationvaluetype>();

  @JsonProperty("comment")
  private String comment = null;

  @JsonProperty("interpretation")
  private String interpretation = null;

  public Observation docid(String docid) {
    this.docid = docid;
    return this;
  }

   /**
   * Get docid
   * @return docid
  **/
  @JsonProperty("docid")
  @ApiModelProperty(value = "")
  public String getDocid() {
    return docid;
  }

  public void setDocid(String docid) {
    this.docid = docid;
  }

  public Observation systemid(String systemid) {
    this.systemid = systemid;
    return this;
  }

   /**
   * Get systemid
   * @return systemid
  **/
  @JsonProperty("systemid")
  @ApiModelProperty(value = "")
  public String getSystemid() {
    return systemid;
  }

  public void setSystemid(String systemid) {
    this.systemid = systemid;
  }

  public Observation userid(String userid) {
    this.userid = userid;
    return this;
  }

   /**
   * Get userid
   * @return userid
  **/
  @JsonProperty("userid")
  @ApiModelProperty(value = "")
  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public Observation subjectid(String subjectid) {
    this.subjectid = subjectid;
    return this;
  }

   /**
   * Get subjectid
   * @return subjectid
  **/
  @JsonProperty("subjectid")
  @ApiModelProperty(value = "")
  public String getSubjectid() {
    return subjectid;
  }

  public void setSubjectid(String subjectid) {
    this.subjectid = subjectid;
  }

  public Observation observationtype(String observationtype) {
    this.observationtype = observationtype;
    return this;
  }

   /**
   * Get observationtype
   * @return observationtype
  **/
  @JsonProperty("observationtype")
  @ApiModelProperty(value = "")
  public String getObservationtype() {
    return observationtype;
  }

  public void setObservationtype(String observationtype) {
    this.observationtype = observationtype;
  }

  public Observation observationcreateddatetime(String observationcreateddatetime) {
    this.observationcreateddatetime = observationcreateddatetime;
    return this;
  }

   /**
   * Get observationcreateddatetime
   * @return observationcreateddatetime
  **/
  @JsonProperty("observationcreateddatetime")
  @ApiModelProperty(value = "")
  public String getObservationcreateddatetime() {
    return observationcreateddatetime;
  }

  public void setObservationcreateddatetime(String observationcreateddatetime) {
    this.observationcreateddatetime = observationcreateddatetime;
  }

  public Observation location(Location location) {
    this.location = location;
    return this;
  }

   /**
   * Get location
   * @return location
  **/
  @JsonProperty("location")
  @ApiModelProperty(value = "")
  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Observation valuecomponents(List<Observationvaluetype> valuecomponents) {
    this.valuecomponents = valuecomponents;
    return this;
  }

  public Observation addValuecomponentsItem(Observationvaluetype valuecomponentsItem) {
    this.valuecomponents.add(valuecomponentsItem);
    return this;
  }

   /**
   * Get valuecomponents
   * @return valuecomponents
  **/
  @JsonProperty("valuecomponents")
  @ApiModelProperty(value = "")
  public List<Observationvaluetype> getValuecomponents() {
    return valuecomponents;
  }

  public void setValuecomponents(List<Observationvaluetype> valuecomponents) {
    this.valuecomponents = valuecomponents;
  }

  public Observation comment(String comment) {
    this.comment = comment;
    return this;
  }

   /**
   * Get comment
   * @return comment
  **/
  @JsonProperty("comment")
  @ApiModelProperty(value = "")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Observation interpretation(String interpretation) {
    this.interpretation = interpretation;
    return this;
  }

   /**
   * Get interpretation
   * @return interpretation
  **/
  @JsonProperty("interpretation")
  @ApiModelProperty(value = "")
  public String getInterpretation() {
    return interpretation;
  }

  public void setInterpretation(String interpretation) {
    this.interpretation = interpretation;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Observation observation = (Observation) o;
    return Objects.equals(this.docid, observation.docid) &&
        Objects.equals(this.systemid, observation.systemid) &&
        Objects.equals(this.userid, observation.userid) &&
        Objects.equals(this.subjectid, observation.subjectid) &&
        Objects.equals(this.observationtype, observation.observationtype) &&
        Objects.equals(this.observationcreateddatetime, observation.observationcreateddatetime) &&
        Objects.equals(this.location, observation.location) &&
        Objects.equals(this.valuecomponents, observation.valuecomponents) &&
        Objects.equals(this.comment, observation.comment) &&
        Objects.equals(this.interpretation, observation.interpretation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(docid, systemid, userid, subjectid, observationtype, observationcreateddatetime, location, valuecomponents, comment, interpretation);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Observation {\n");
    
    sb.append("    docid: ").append(toIndentedString(docid)).append("\n");
    sb.append("    systemid: ").append(toIndentedString(systemid)).append("\n");
    sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
    sb.append("    subjectid: ").append(toIndentedString(subjectid)).append("\n");
    sb.append("    observationtype: ").append(toIndentedString(observationtype)).append("\n");
    sb.append("    observationcreateddatetime: ").append(toIndentedString(observationcreateddatetime)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    valuecomponents: ").append(toIndentedString(valuecomponents)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    interpretation: ").append(toIndentedString(interpretation)).append("\n");
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
}

