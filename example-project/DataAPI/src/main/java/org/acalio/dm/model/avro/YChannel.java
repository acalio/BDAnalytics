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
public class YChannel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -7170714976731637951L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"YChannel\",\"namespace\":\"org.acalio.dm.model.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"title\",\"type\":\"string\"},{\"name\":\"description\",\"type\":\"string\"},{\"name\":\"country\",\"type\":[\"null\",\"string\"]},{\"name\":\"verified\",\"type\":\"boolean\"},{\"name\":\"likedVideosPlaylistId\",\"type\":\"string\"},{\"name\":\"viewCount\",\"type\":\"long\"},{\"name\":\"subscriberCount\",\"type\":\"long\"},{\"name\":\"hiddensubscribercount\",\"type\":\"boolean\"},{\"name\":\"videoCount\",\"type\":\"long\"},{\"name\":\"publishedAt\",\"type\":{\"type\":\"long\",\"logitcalType\":\"timestamp-millis\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<YChannel> ENCODER =
      new BinaryMessageEncoder<YChannel>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<YChannel> DECODER =
      new BinaryMessageDecoder<YChannel>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<YChannel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<YChannel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<YChannel> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<YChannel>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this YChannel to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a YChannel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a YChannel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static YChannel fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.CharSequence id;
   private java.lang.CharSequence title;
   private java.lang.CharSequence description;
   private java.lang.CharSequence country;
   private boolean verified;
   private java.lang.CharSequence likedVideosPlaylistId;
   private long viewCount;
   private long subscriberCount;
   private boolean hiddensubscribercount;
   private long videoCount;
   private long publishedAt;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public YChannel() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param title The new value for title
   * @param description The new value for description
   * @param country The new value for country
   * @param verified The new value for verified
   * @param likedVideosPlaylistId The new value for likedVideosPlaylistId
   * @param viewCount The new value for viewCount
   * @param subscriberCount The new value for subscriberCount
   * @param hiddensubscribercount The new value for hiddensubscribercount
   * @param videoCount The new value for videoCount
   * @param publishedAt The new value for publishedAt
   */
  public YChannel(java.lang.CharSequence id, java.lang.CharSequence title, java.lang.CharSequence description, java.lang.CharSequence country, java.lang.Boolean verified, java.lang.CharSequence likedVideosPlaylistId, java.lang.Long viewCount, java.lang.Long subscriberCount, java.lang.Boolean hiddensubscribercount, java.lang.Long videoCount, java.lang.Long publishedAt) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.country = country;
    this.verified = verified;
    this.likedVideosPlaylistId = likedVideosPlaylistId;
    this.viewCount = viewCount;
    this.subscriberCount = subscriberCount;
    this.hiddensubscribercount = hiddensubscribercount;
    this.videoCount = videoCount;
    this.publishedAt = publishedAt;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return title;
    case 2: return description;
    case 3: return country;
    case 4: return verified;
    case 5: return likedVideosPlaylistId;
    case 6: return viewCount;
    case 7: return subscriberCount;
    case 8: return hiddensubscribercount;
    case 9: return videoCount;
    case 10: return publishedAt;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.CharSequence)value$; break;
    case 1: title = (java.lang.CharSequence)value$; break;
    case 2: description = (java.lang.CharSequence)value$; break;
    case 3: country = (java.lang.CharSequence)value$; break;
    case 4: verified = (java.lang.Boolean)value$; break;
    case 5: likedVideosPlaylistId = (java.lang.CharSequence)value$; break;
    case 6: viewCount = (java.lang.Long)value$; break;
    case 7: subscriberCount = (java.lang.Long)value$; break;
    case 8: hiddensubscribercount = (java.lang.Boolean)value$; break;
    case 9: videoCount = (java.lang.Long)value$; break;
    case 10: publishedAt = (java.lang.Long)value$; break;
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
   * Gets the value of the 'title' field.
   * @return The value of the 'title' field.
   */
  public java.lang.CharSequence getTitle() {
    return title;
  }


  /**
   * Sets the value of the 'title' field.
   * @param value the value to set.
   */
  public void setTitle(java.lang.CharSequence value) {
    this.title = value;
  }

  /**
   * Gets the value of the 'description' field.
   * @return The value of the 'description' field.
   */
  public java.lang.CharSequence getDescription() {
    return description;
  }


  /**
   * Sets the value of the 'description' field.
   * @param value the value to set.
   */
  public void setDescription(java.lang.CharSequence value) {
    this.description = value;
  }

  /**
   * Gets the value of the 'country' field.
   * @return The value of the 'country' field.
   */
  public java.lang.CharSequence getCountry() {
    return country;
  }


  /**
   * Sets the value of the 'country' field.
   * @param value the value to set.
   */
  public void setCountry(java.lang.CharSequence value) {
    this.country = value;
  }

  /**
   * Gets the value of the 'verified' field.
   * @return The value of the 'verified' field.
   */
  public boolean getVerified() {
    return verified;
  }


  /**
   * Sets the value of the 'verified' field.
   * @param value the value to set.
   */
  public void setVerified(boolean value) {
    this.verified = value;
  }

  /**
   * Gets the value of the 'likedVideosPlaylistId' field.
   * @return The value of the 'likedVideosPlaylistId' field.
   */
  public java.lang.CharSequence getLikedVideosPlaylistId() {
    return likedVideosPlaylistId;
  }


  /**
   * Sets the value of the 'likedVideosPlaylistId' field.
   * @param value the value to set.
   */
  public void setLikedVideosPlaylistId(java.lang.CharSequence value) {
    this.likedVideosPlaylistId = value;
  }

  /**
   * Gets the value of the 'viewCount' field.
   * @return The value of the 'viewCount' field.
   */
  public long getViewCount() {
    return viewCount;
  }


  /**
   * Sets the value of the 'viewCount' field.
   * @param value the value to set.
   */
  public void setViewCount(long value) {
    this.viewCount = value;
  }

  /**
   * Gets the value of the 'subscriberCount' field.
   * @return The value of the 'subscriberCount' field.
   */
  public long getSubscriberCount() {
    return subscriberCount;
  }


  /**
   * Sets the value of the 'subscriberCount' field.
   * @param value the value to set.
   */
  public void setSubscriberCount(long value) {
    this.subscriberCount = value;
  }

  /**
   * Gets the value of the 'hiddensubscribercount' field.
   * @return The value of the 'hiddensubscribercount' field.
   */
  public boolean getHiddensubscribercount() {
    return hiddensubscribercount;
  }


  /**
   * Sets the value of the 'hiddensubscribercount' field.
   * @param value the value to set.
   */
  public void setHiddensubscribercount(boolean value) {
    this.hiddensubscribercount = value;
  }

  /**
   * Gets the value of the 'videoCount' field.
   * @return The value of the 'videoCount' field.
   */
  public long getVideoCount() {
    return videoCount;
  }


  /**
   * Sets the value of the 'videoCount' field.
   * @param value the value to set.
   */
  public void setVideoCount(long value) {
    this.videoCount = value;
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
   * Creates a new YChannel RecordBuilder.
   * @return A new YChannel RecordBuilder
   */
  public static org.acalio.dm.model.avro.YChannel.Builder newBuilder() {
    return new org.acalio.dm.model.avro.YChannel.Builder();
  }

  /**
   * Creates a new YChannel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new YChannel RecordBuilder
   */
  public static org.acalio.dm.model.avro.YChannel.Builder newBuilder(org.acalio.dm.model.avro.YChannel.Builder other) {
    if (other == null) {
      return new org.acalio.dm.model.avro.YChannel.Builder();
    } else {
      return new org.acalio.dm.model.avro.YChannel.Builder(other);
    }
  }

  /**
   * Creates a new YChannel RecordBuilder by copying an existing YChannel instance.
   * @param other The existing instance to copy.
   * @return A new YChannel RecordBuilder
   */
  public static org.acalio.dm.model.avro.YChannel.Builder newBuilder(org.acalio.dm.model.avro.YChannel other) {
    if (other == null) {
      return new org.acalio.dm.model.avro.YChannel.Builder();
    } else {
      return new org.acalio.dm.model.avro.YChannel.Builder(other);
    }
  }

  /**
   * RecordBuilder for YChannel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<YChannel>
    implements org.apache.avro.data.RecordBuilder<YChannel> {

    private java.lang.CharSequence id;
    private java.lang.CharSequence title;
    private java.lang.CharSequence description;
    private java.lang.CharSequence country;
    private boolean verified;
    private java.lang.CharSequence likedVideosPlaylistId;
    private long viewCount;
    private long subscriberCount;
    private boolean hiddensubscribercount;
    private long videoCount;
    private long publishedAt;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.acalio.dm.model.avro.YChannel.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.title)) {
        this.title = data().deepCopy(fields()[1].schema(), other.title);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.description)) {
        this.description = data().deepCopy(fields()[2].schema(), other.description);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.country)) {
        this.country = data().deepCopy(fields()[3].schema(), other.country);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.verified)) {
        this.verified = data().deepCopy(fields()[4].schema(), other.verified);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.likedVideosPlaylistId)) {
        this.likedVideosPlaylistId = data().deepCopy(fields()[5].schema(), other.likedVideosPlaylistId);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.viewCount)) {
        this.viewCount = data().deepCopy(fields()[6].schema(), other.viewCount);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.subscriberCount)) {
        this.subscriberCount = data().deepCopy(fields()[7].schema(), other.subscriberCount);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
      if (isValidValue(fields()[8], other.hiddensubscribercount)) {
        this.hiddensubscribercount = data().deepCopy(fields()[8].schema(), other.hiddensubscribercount);
        fieldSetFlags()[8] = other.fieldSetFlags()[8];
      }
      if (isValidValue(fields()[9], other.videoCount)) {
        this.videoCount = data().deepCopy(fields()[9].schema(), other.videoCount);
        fieldSetFlags()[9] = other.fieldSetFlags()[9];
      }
      if (isValidValue(fields()[10], other.publishedAt)) {
        this.publishedAt = data().deepCopy(fields()[10].schema(), other.publishedAt);
        fieldSetFlags()[10] = other.fieldSetFlags()[10];
      }
    }

    /**
     * Creates a Builder by copying an existing YChannel instance
     * @param other The existing instance to copy.
     */
    private Builder(org.acalio.dm.model.avro.YChannel other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.title)) {
        this.title = data().deepCopy(fields()[1].schema(), other.title);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.description)) {
        this.description = data().deepCopy(fields()[2].schema(), other.description);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.country)) {
        this.country = data().deepCopy(fields()[3].schema(), other.country);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.verified)) {
        this.verified = data().deepCopy(fields()[4].schema(), other.verified);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.likedVideosPlaylistId)) {
        this.likedVideosPlaylistId = data().deepCopy(fields()[5].schema(), other.likedVideosPlaylistId);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.viewCount)) {
        this.viewCount = data().deepCopy(fields()[6].schema(), other.viewCount);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.subscriberCount)) {
        this.subscriberCount = data().deepCopy(fields()[7].schema(), other.subscriberCount);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.hiddensubscribercount)) {
        this.hiddensubscribercount = data().deepCopy(fields()[8].schema(), other.hiddensubscribercount);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.videoCount)) {
        this.videoCount = data().deepCopy(fields()[9].schema(), other.videoCount);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.publishedAt)) {
        this.publishedAt = data().deepCopy(fields()[10].schema(), other.publishedAt);
        fieldSetFlags()[10] = true;
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
    public org.acalio.dm.model.avro.YChannel.Builder setId(java.lang.CharSequence value) {
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
    public org.acalio.dm.model.avro.YChannel.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'title' field.
      * @return The value.
      */
    public java.lang.CharSequence getTitle() {
      return title;
    }


    /**
      * Sets the value of the 'title' field.
      * @param value The value of 'title'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder setTitle(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.title = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'title' field has been set.
      * @return True if the 'title' field has been set, false otherwise.
      */
    public boolean hasTitle() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'title' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder clearTitle() {
      title = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'description' field.
      * @return The value.
      */
    public java.lang.CharSequence getDescription() {
      return description;
    }


    /**
      * Sets the value of the 'description' field.
      * @param value The value of 'description'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder setDescription(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.description = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'description' field has been set.
      * @return True if the 'description' field has been set, false otherwise.
      */
    public boolean hasDescription() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'description' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder clearDescription() {
      description = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'country' field.
      * @return The value.
      */
    public java.lang.CharSequence getCountry() {
      return country;
    }


    /**
      * Sets the value of the 'country' field.
      * @param value The value of 'country'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder setCountry(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.country = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'country' field has been set.
      * @return True if the 'country' field has been set, false otherwise.
      */
    public boolean hasCountry() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'country' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder clearCountry() {
      country = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'verified' field.
      * @return The value.
      */
    public boolean getVerified() {
      return verified;
    }


    /**
      * Sets the value of the 'verified' field.
      * @param value The value of 'verified'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder setVerified(boolean value) {
      validate(fields()[4], value);
      this.verified = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'verified' field has been set.
      * @return True if the 'verified' field has been set, false otherwise.
      */
    public boolean hasVerified() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'verified' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder clearVerified() {
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'likedVideosPlaylistId' field.
      * @return The value.
      */
    public java.lang.CharSequence getLikedVideosPlaylistId() {
      return likedVideosPlaylistId;
    }


    /**
      * Sets the value of the 'likedVideosPlaylistId' field.
      * @param value The value of 'likedVideosPlaylistId'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder setLikedVideosPlaylistId(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.likedVideosPlaylistId = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'likedVideosPlaylistId' field has been set.
      * @return True if the 'likedVideosPlaylistId' field has been set, false otherwise.
      */
    public boolean hasLikedVideosPlaylistId() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'likedVideosPlaylistId' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder clearLikedVideosPlaylistId() {
      likedVideosPlaylistId = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'viewCount' field.
      * @return The value.
      */
    public long getViewCount() {
      return viewCount;
    }


    /**
      * Sets the value of the 'viewCount' field.
      * @param value The value of 'viewCount'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder setViewCount(long value) {
      validate(fields()[6], value);
      this.viewCount = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'viewCount' field has been set.
      * @return True if the 'viewCount' field has been set, false otherwise.
      */
    public boolean hasViewCount() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'viewCount' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder clearViewCount() {
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'subscriberCount' field.
      * @return The value.
      */
    public long getSubscriberCount() {
      return subscriberCount;
    }


    /**
      * Sets the value of the 'subscriberCount' field.
      * @param value The value of 'subscriberCount'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder setSubscriberCount(long value) {
      validate(fields()[7], value);
      this.subscriberCount = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'subscriberCount' field has been set.
      * @return True if the 'subscriberCount' field has been set, false otherwise.
      */
    public boolean hasSubscriberCount() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'subscriberCount' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder clearSubscriberCount() {
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'hiddensubscribercount' field.
      * @return The value.
      */
    public boolean getHiddensubscribercount() {
      return hiddensubscribercount;
    }


    /**
      * Sets the value of the 'hiddensubscribercount' field.
      * @param value The value of 'hiddensubscribercount'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder setHiddensubscribercount(boolean value) {
      validate(fields()[8], value);
      this.hiddensubscribercount = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'hiddensubscribercount' field has been set.
      * @return True if the 'hiddensubscribercount' field has been set, false otherwise.
      */
    public boolean hasHiddensubscribercount() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'hiddensubscribercount' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder clearHiddensubscribercount() {
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'videoCount' field.
      * @return The value.
      */
    public long getVideoCount() {
      return videoCount;
    }


    /**
      * Sets the value of the 'videoCount' field.
      * @param value The value of 'videoCount'.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder setVideoCount(long value) {
      validate(fields()[9], value);
      this.videoCount = value;
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the 'videoCount' field has been set.
      * @return True if the 'videoCount' field has been set, false otherwise.
      */
    public boolean hasVideoCount() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'videoCount' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder clearVideoCount() {
      fieldSetFlags()[9] = false;
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
    public org.acalio.dm.model.avro.YChannel.Builder setPublishedAt(long value) {
      validate(fields()[10], value);
      this.publishedAt = value;
      fieldSetFlags()[10] = true;
      return this;
    }

    /**
      * Checks whether the 'publishedAt' field has been set.
      * @return True if the 'publishedAt' field has been set, false otherwise.
      */
    public boolean hasPublishedAt() {
      return fieldSetFlags()[10];
    }


    /**
      * Clears the value of the 'publishedAt' field.
      * @return This builder.
      */
    public org.acalio.dm.model.avro.YChannel.Builder clearPublishedAt() {
      fieldSetFlags()[10] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public YChannel build() {
      try {
        YChannel record = new YChannel();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.title = fieldSetFlags()[1] ? this.title : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.description = fieldSetFlags()[2] ? this.description : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.country = fieldSetFlags()[3] ? this.country : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.verified = fieldSetFlags()[4] ? this.verified : (java.lang.Boolean) defaultValue(fields()[4]);
        record.likedVideosPlaylistId = fieldSetFlags()[5] ? this.likedVideosPlaylistId : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.viewCount = fieldSetFlags()[6] ? this.viewCount : (java.lang.Long) defaultValue(fields()[6]);
        record.subscriberCount = fieldSetFlags()[7] ? this.subscriberCount : (java.lang.Long) defaultValue(fields()[7]);
        record.hiddensubscribercount = fieldSetFlags()[8] ? this.hiddensubscribercount : (java.lang.Boolean) defaultValue(fields()[8]);
        record.videoCount = fieldSetFlags()[9] ? this.videoCount : (java.lang.Long) defaultValue(fields()[9]);
        record.publishedAt = fieldSetFlags()[10] ? this.publishedAt : (java.lang.Long) defaultValue(fields()[10]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<YChannel>
    WRITER$ = (org.apache.avro.io.DatumWriter<YChannel>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<YChannel>
    READER$ = (org.apache.avro.io.DatumReader<YChannel>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.id);

    out.writeString(this.title);

    out.writeString(this.description);

    if (this.country == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.country);
    }

    out.writeBoolean(this.verified);

    out.writeString(this.likedVideosPlaylistId);

    out.writeLong(this.viewCount);

    out.writeLong(this.subscriberCount);

    out.writeBoolean(this.hiddensubscribercount);

    out.writeLong(this.videoCount);

    out.writeLong(this.publishedAt);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.id = in.readString(this.id instanceof Utf8 ? (Utf8)this.id : null);

      this.title = in.readString(this.title instanceof Utf8 ? (Utf8)this.title : null);

      this.description = in.readString(this.description instanceof Utf8 ? (Utf8)this.description : null);

      if (in.readIndex() != 1) {
        in.readNull();
        this.country = null;
      } else {
        this.country = in.readString(this.country instanceof Utf8 ? (Utf8)this.country : null);
      }

      this.verified = in.readBoolean();

      this.likedVideosPlaylistId = in.readString(this.likedVideosPlaylistId instanceof Utf8 ? (Utf8)this.likedVideosPlaylistId : null);

      this.viewCount = in.readLong();

      this.subscriberCount = in.readLong();

      this.hiddensubscribercount = in.readBoolean();

      this.videoCount = in.readLong();

      this.publishedAt = in.readLong();

    } else {
      for (int i = 0; i < 11; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.id = in.readString(this.id instanceof Utf8 ? (Utf8)this.id : null);
          break;

        case 1:
          this.title = in.readString(this.title instanceof Utf8 ? (Utf8)this.title : null);
          break;

        case 2:
          this.description = in.readString(this.description instanceof Utf8 ? (Utf8)this.description : null);
          break;

        case 3:
          if (in.readIndex() != 1) {
            in.readNull();
            this.country = null;
          } else {
            this.country = in.readString(this.country instanceof Utf8 ? (Utf8)this.country : null);
          }
          break;

        case 4:
          this.verified = in.readBoolean();
          break;

        case 5:
          this.likedVideosPlaylistId = in.readString(this.likedVideosPlaylistId instanceof Utf8 ? (Utf8)this.likedVideosPlaylistId : null);
          break;

        case 6:
          this.viewCount = in.readLong();
          break;

        case 7:
          this.subscriberCount = in.readLong();
          break;

        case 8:
          this.hiddensubscribercount = in.readBoolean();
          break;

        case 9:
          this.videoCount = in.readLong();
          break;

        case 10:
          this.publishedAt = in.readLong();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










