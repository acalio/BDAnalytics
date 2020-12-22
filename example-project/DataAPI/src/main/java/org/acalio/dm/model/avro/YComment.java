/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.acalio.dm.model.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class YComment extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -7220496500113728551L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"YComment\",\"namespace\":\"org.acalio.dm.model.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"text\",\"type\":\"string\"},{\"name\":\"videoId\",\"type\":\"string\"},{\"name\":\"authorChannelId\",\"type\":\"string\"},{\"name\":\"authorChannelUrl\",\"type\":\"string\"},{\"name\":\"authorDisplayName\",\"type\":\"string\"},{\"name\":\"likeCount\",\"type\":\"long\"},{\"name\":\"publishedAt\",\"type\":{\"type\":\"long\",\"logitcalType\":\"timestamp-millis\"}},{\"name\":\"parentID\",\"type\":[\"null\",\"string\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<YComment> ENCODER =
      new BinaryMessageEncoder<YComment>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<YComment> DECODER =
      new BinaryMessageDecoder<YComment>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<YComment> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<YComment> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<YComment> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<YComment>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this YComment to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a YComment from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a YComment instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static YComment fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.CharSequence id;
   private java.lang.CharSequence text;
   private java.lang.CharSequence videoId;
   private java.lang.CharSequence authorChannelId;
   private java.lang.CharSequence authorChannelUrl;
   private java.lang.CharSequence authorDisplayName;
   private long likeCount;
   private long publishedAt;
   private java.lang.CharSequence parentID;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public YComment() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param text The new value for text
   * @param videoId The new value for videoId
   * @param authorChannelId The new value for authorChannelId
   * @param authorChannelUrl The new value for authorChannelUrl
   * @param authorDisplayName The new value for authorDisplayName
   * @param likeCount The new value for likeCount
   * @param publishedAt The new value for publishedAt
   * @param parentID The new value for parentID
   */
  public YComment(java.lang.CharSequence id, java.lang.CharSequence text, java.lang.CharSequence videoId, java.lang.CharSequence authorChannelId, java.lang.CharSequence authorChannelUrl, java.lang.CharSequence authorDisplayName, java.lang.Long likeCount, java.lang.Long publishedAt, java.lang.CharSequence parentID) {
    this.id = id;
    this.text = text;
    this.videoId = videoId;
    this.authorChannelId = authorChannelId;
    this.authorChannelUrl = authorChannelUrl;
    this.authorDisplayName = authorDisplayName;
    this.likeCount = likeCount;
    this.publishedAt = publishedAt;
    this.parentID = parentID;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return text;
    case 2: return videoId;
    case 3: return authorChannelId;
    case 4: return authorChannelUrl;
    case 5: return authorDisplayName;
    case 6: return likeCount;
    case 7: return publishedAt;
    case 8: return parentID;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.CharSequence)value$; break;
    case 1: text = (java.lang.CharSequence)value$; break;
    case 2: videoId = (java.lang.CharSequence)value$; break;
    case 3: authorChannelId = (java.lang.CharSequence)value$; break;
    case 4: authorChannelUrl = (java.lang.CharSequence)value$; break;
    case 5: authorDisplayName = (java.lang.CharSequence)value$; break;
    case 6: likeCount = (java.lang.Long)value$; break;
    case 7: publishedAt = (java.lang.Long)value$; break;
    case 8: parentID = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.CharSequence getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.CharSequence value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'text' field.
   * @return The value of the 'text' field.
   */
  public java.lang.CharSequence getText() {
    return text;
  }


  /**
   * Sets the value of the 'text' field.
   * @param value the value to set.
   */
  public void setText(java.lang.CharSequence value) {
    this.text = value;
  }

  /**
   * Gets the value of the 'videoId' field.
   * @return The value of the 'videoId' field.
   */
  public java.lang.CharSequence getVideoId() {
    return videoId;
  }


  /**
   * Sets the value of the 'videoId' field.
   * @param value the value to set.
   */
  public void setVideoId(java.lang.CharSequence value) {
    this.videoId = value;
  }

  /**
   * Gets the value of the 'authorChannelId' field.
   * @return The value of the 'authorChannelId' field.
   */
  public java.lang.CharSequence getAuthorChannelId() {
    return authorChannelId;
  }


  /**
   * Sets the value of the 'authorChannelId' field.
   * @param value the value to set.
   */
  public void setAuthorChannelId(java.lang.CharSequence value) {
    this.authorChannelId = value;
  }

  /**
   * Gets the value of the 'authorChannelUrl' field.
   * @return The value of the 'authorChannelUrl' field.
   */
  public java.lang.CharSequence getAuthorChannelUrl() {
    return authorChannelUrl;
  }


  /**
   * Sets the value of the 'authorChannelUrl' field.
   * @param value the value to set.
   */
  public void setAuthorChannelUrl(java.lang.CharSequence value) {
    this.authorChannelUrl = value;
  }

  /**
   * Gets the value of the 'authorDisplayName' field.
   * @return The value of the 'authorDisplayName' field.
   */
  public java.lang.CharSequence getAuthorDisplayName() {
    return authorDisplayName;
  }


  /**
   * Sets the value of the 'authorDisplayName' field.
   * @param value the value to set.
   */
  public void setAuthorDisplayName(java.lang.CharSequence value) {
    this.authorDisplayName = value;
  }

  /**
   * Gets the value of the 'likeCount' field.
   * @return The value of the 'likeCount' field.
   */
  public long getLikeCount() {
    return likeCount;
  }


  /**
   * Sets the value of the 'likeCount' field.
   * @param value the value to set.
   */
  public void setLikeCount(long value) {
    this.likeCount = value;
  }

  /**
   * Gets the value of the 'publishedAt' field.
   * @return The value of the 'publishedAt' field.
   */
  public long getPublishedAt() {
    return publishedAt;
  }


  /**
   * Sets the value of the 'publishedAt' field.
   * @param value the value to set.
   */
  public void setPublishedAt(long value) {
    this.publishedAt = value;
  }

  /**
   * Gets the value of the 'parentID' field.
   * @return The value of the 'parentID' field.
   */
  public java.lang.CharSequence getParentID() {
    return parentID;
  }


  /**
   * Sets the value of the 'parentID' field.
   * @param value the value to set.
   */
  public void setParentID(java.lang.CharSequence value) {
    this.parentID = value;
  }

  /**
   * Creates a new YComment RecordBuilder.
   * @return A new YComment RecordBuilder
   */
  public static org.acalio.dm.model.avro.YComment.Builder newBuilder() {
    return new org.acalio.dm.model.avro.YComment.Builder();
  }

  /**
   * Creates a new YComment RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new YComment RecordBuilder
   */
  public static org.acalio.dm.model.avro.YComment.Builder newBuilder(org.acalio.dm.model.avro.YComment.Builder other) {
    if (other == null) {
      return new org.acalio.dm.model.avro.YComment.Builder();
    } else {
      return new org.acalio.dm.model.avro.YComment.Builder(other);
    }
  }

  /**
   * Creates a new YComment RecordBuilder by copying an existing YComment instance.
   * @param other The existing instance to copy.
   * @return A new YComment RecordBuilder
   */
  public static org.acalio.dm.model.avro.YComment.Builder newBuilder(org.acalio.dm.model.avro.YComment other) {
    if (other == null) {
      return new org.acalio.dm.model.avro.YComment.Builder();
    } else {
      return new org.acalio.dm.model.avro.YComment.Builder(other);
    }
  }

  /**
   * RecordBuilder for YComment instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<YComment>
    implements org.apache.avro.data.RecordBuilder<YComment> {

    private java.lang.CharSequence id;
    private java.lang.CharSequence text;
    private java.lang.CharSequence videoId;
    private java.lang.CharSequence authorChannelId;
    private java.lang.CharSequence authorChannelUrl;
    private java.lang.CharSequence authorDisplayName;
    private long likeCount;
    private long publishedAt;
    private java.lang.CharSequence parentID;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.acalio.dm.model.avro.YComment.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.text)) {
        this.text = data().deepCopy(fields()[1].schema(), other.text);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.videoId)) {
        this.videoId = data().deepCopy(fields()[2].schema(), other.videoId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.authorChannelId)) {
        this.authorChannelId = data().deepCopy(fields()[3].schema(), other.authorChannelId);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.authorChannelUrl)) {
        this.authorChannelUrl = data().deepCopy(fields()[4].schema(), other.authorChannelUrl);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.authorDisplayName)) {
        this.authorDisplayName = data().deepCopy(fields()[5].schema(), other.authorDisplayName);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.likeCount)) {
        this.likeCount = data().deepCopy(fields()[6].schema(), other.likeCount);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.publishedAt)) {
        this.publishedAt = data().deepCopy(fields()[7].schema(), other.publishedAt);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
      if (isValidValue(fields()[8], other.parentID)) {
        this.parentID = data().deepCopy(fields()[8].schema(), other.parentID);
        fieldSetFlags()[8] = other.fieldSetFlags()[8];
      }
    }

    /**
     * Creates a Builder by copying an existing YComment instance
     * @param other The existing instance to copy.
     */
    private Builder(org.acalio.dm.model.avro.YComment other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.text)) {
        this.text = data().deepCopy(fields()[1].schema(), other.text);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.videoId)) {
        this.videoId = data().deepCopy(fields()[2].schema(), other.videoId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.authorChannelId)) {
        this.authorChannelId = data().deepCopy(fields()[3].schema(), other.authorChannelId);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.authorChannelUrl)) {
        this.authorChannelUrl = data().deepCopy(fields()[4].schema(), other.authorChannelUrl);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.authorDisplayName)) {
        this.authorDisplayName = data().deepCopy(fields()[5].schema(), other.authorDisplayName);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.likeCount)) {
        this.likeCount = data().deepCopy(fields()[6].schema(), other.likeCount);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.publishedAt)) {
        this.publishedAt = data().deepCopy(fields()[7].schema(), other.publishedAt);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.parentID)) {
        this.parentID = data().deepCopy(fields()[8].schema(), other.parentID);
        fieldSetFlags()[8] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.CharSequence getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder setId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'text' field.
      * @return The value.
      */
    public java.lang.CharSequence getText() {
      return text;
    }


    /**
      * Sets the value of the 'text' field.
      * @param value The value of 'text'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder setText(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.text = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'text' field has been set.
      * @return True if the 'text' field has been set, false otherwise.
      */
    public boolean hasText() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'text' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder clearText() {
      text = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'videoId' field.
      * @return The value.
      */
    public java.lang.CharSequence getVideoId() {
      return videoId;
    }


    /**
      * Sets the value of the 'videoId' field.
      * @param value The value of 'videoId'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder setVideoId(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.videoId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'videoId' field has been set.
      * @return True if the 'videoId' field has been set, false otherwise.
      */
    public boolean hasVideoId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'videoId' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder clearVideoId() {
      videoId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'authorChannelId' field.
      * @return The value.
      */
    public java.lang.CharSequence getAuthorChannelId() {
      return authorChannelId;
    }


    /**
      * Sets the value of the 'authorChannelId' field.
      * @param value The value of 'authorChannelId'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder setAuthorChannelId(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.authorChannelId = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'authorChannelId' field has been set.
      * @return True if the 'authorChannelId' field has been set, false otherwise.
      */
    public boolean hasAuthorChannelId() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'authorChannelId' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder clearAuthorChannelId() {
      authorChannelId = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'authorChannelUrl' field.
      * @return The value.
      */
    public java.lang.CharSequence getAuthorChannelUrl() {
      return authorChannelUrl;
    }


    /**
      * Sets the value of the 'authorChannelUrl' field.
      * @param value The value of 'authorChannelUrl'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder setAuthorChannelUrl(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.authorChannelUrl = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'authorChannelUrl' field has been set.
      * @return True if the 'authorChannelUrl' field has been set, false otherwise.
      */
    public boolean hasAuthorChannelUrl() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'authorChannelUrl' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder clearAuthorChannelUrl() {
      authorChannelUrl = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'authorDisplayName' field.
      * @return The value.
      */
    public java.lang.CharSequence getAuthorDisplayName() {
      return authorDisplayName;
    }


    /**
      * Sets the value of the 'authorDisplayName' field.
      * @param value The value of 'authorDisplayName'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder setAuthorDisplayName(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.authorDisplayName = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'authorDisplayName' field has been set.
      * @return True if the 'authorDisplayName' field has been set, false otherwise.
      */
    public boolean hasAuthorDisplayName() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'authorDisplayName' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder clearAuthorDisplayName() {
      authorDisplayName = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'likeCount' field.
      * @return The value.
      */
    public long getLikeCount() {
      return likeCount;
    }


    /**
      * Sets the value of the 'likeCount' field.
      * @param value The value of 'likeCount'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder setLikeCount(long value) {
      validate(fields()[6], value);
      this.likeCount = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'likeCount' field has been set.
      * @return True if the 'likeCount' field has been set, false otherwise.
      */
    public boolean hasLikeCount() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'likeCount' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder clearLikeCount() {
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'publishedAt' field.
      * @return The value.
      */
    public long getPublishedAt() {
      return publishedAt;
    }


    /**
      * Sets the value of the 'publishedAt' field.
      * @param value The value of 'publishedAt'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder setPublishedAt(long value) {
      validate(fields()[7], value);
      this.publishedAt = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'publishedAt' field has been set.
      * @return True if the 'publishedAt' field has been set, false otherwise.
      */
    public boolean hasPublishedAt() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'publishedAt' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder clearPublishedAt() {
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'parentID' field.
      * @return The value.
      */
    public java.lang.CharSequence getParentID() {
      return parentID;
    }


    /**
      * Sets the value of the 'parentID' field.
      * @param value The value of 'parentID'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder setParentID(java.lang.CharSequence value) {
      validate(fields()[8], value);
      this.parentID = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'parentID' field has been set.
      * @return True if the 'parentID' field has been set, false otherwise.
      */
    public boolean hasParentID() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'parentID' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YComment.Builder clearParentID() {
      parentID = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public YComment build() {
      try {
        YComment record = new YComment();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.text = fieldSetFlags()[1] ? this.text : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.videoId = fieldSetFlags()[2] ? this.videoId : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.authorChannelId = fieldSetFlags()[3] ? this.authorChannelId : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.authorChannelUrl = fieldSetFlags()[4] ? this.authorChannelUrl : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.authorDisplayName = fieldSetFlags()[5] ? this.authorDisplayName : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.likeCount = fieldSetFlags()[6] ? this.likeCount : (java.lang.Long) defaultValue(fields()[6]);
        record.publishedAt = fieldSetFlags()[7] ? this.publishedAt : (java.lang.Long) defaultValue(fields()[7]);
        record.parentID = fieldSetFlags()[8] ? this.parentID : (java.lang.CharSequence) defaultValue(fields()[8]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<YComment>
    WRITER$ = (org.apache.avro.io.DatumWriter<YComment>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<YComment>
    READER$ = (org.apache.avro.io.DatumReader<YComment>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.id);

    out.writeString(this.text);

    out.writeString(this.videoId);

    out.writeString(this.authorChannelId);

    out.writeString(this.authorChannelUrl);

    out.writeString(this.authorDisplayName);

    out.writeLong(this.likeCount);

    out.writeLong(this.publishedAt);

    if (this.parentID == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.parentID);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.id = in.readString(this.id instanceof Utf8 ? (Utf8)this.id : null);

      this.text = in.readString(this.text instanceof Utf8 ? (Utf8)this.text : null);

      this.videoId = in.readString(this.videoId instanceof Utf8 ? (Utf8)this.videoId : null);

      this.authorChannelId = in.readString(this.authorChannelId instanceof Utf8 ? (Utf8)this.authorChannelId : null);

      this.authorChannelUrl = in.readString(this.authorChannelUrl instanceof Utf8 ? (Utf8)this.authorChannelUrl : null);

      this.authorDisplayName = in.readString(this.authorDisplayName instanceof Utf8 ? (Utf8)this.authorDisplayName : null);

      this.likeCount = in.readLong();

      this.publishedAt = in.readLong();

      if (in.readIndex() != 1) {
        in.readNull();
        this.parentID = null;
      } else {
        this.parentID = in.readString(this.parentID instanceof Utf8 ? (Utf8)this.parentID : null);
      }

    } else {
      for (int i = 0; i < 9; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.id = in.readString(this.id instanceof Utf8 ? (Utf8)this.id : null);
          break;

        case 1:
          this.text = in.readString(this.text instanceof Utf8 ? (Utf8)this.text : null);
          break;

        case 2:
          this.videoId = in.readString(this.videoId instanceof Utf8 ? (Utf8)this.videoId : null);
          break;

        case 3:
          this.authorChannelId = in.readString(this.authorChannelId instanceof Utf8 ? (Utf8)this.authorChannelId : null);
          break;

        case 4:
          this.authorChannelUrl = in.readString(this.authorChannelUrl instanceof Utf8 ? (Utf8)this.authorChannelUrl : null);
          break;

        case 5:
          this.authorDisplayName = in.readString(this.authorDisplayName instanceof Utf8 ? (Utf8)this.authorDisplayName : null);
          break;

        case 6:
          this.likeCount = in.readLong();
          break;

        case 7:
          this.publishedAt = in.readLong();
          break;

        case 8:
          if (in.readIndex() != 1) {
            in.readNull();
            this.parentID = null;
          } else {
            this.parentID = in.readString(this.parentID instanceof Utf8 ? (Utf8)this.parentID : null);
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









