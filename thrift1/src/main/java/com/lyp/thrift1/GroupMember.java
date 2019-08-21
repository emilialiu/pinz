/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package  com.lyp.thrift1;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-05-24")
public class GroupMember implements org.apache.thrift.TBase<GroupMember, GroupMember._Fields>, java.io.Serializable, Cloneable, Comparable<GroupMember> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GroupMember");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField USER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("userId", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField REMARK_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("remarkName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField GROUP_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("groupId", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField JOIN_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("joinTime", org.apache.thrift.protocol.TType.I64, (short)6);
  private static final org.apache.thrift.protocol.TField IS_LOCK_FIELD_DESC = new org.apache.thrift.protocol.TField("isLock", org.apache.thrift.protocol.TType.I32, (short)7);
  private static final org.apache.thrift.protocol.TField LOCK_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("lockDate", org.apache.thrift.protocol.TType.I64, (short)8);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new GroupMemberStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new GroupMemberTupleSchemeFactory();

  public long id; // required
  public long userId; // required
  public java.lang.String remarkName; // required
  public long groupId; // required
  public int type; // required
  public long joinTime; // required
  public int isLock; // required
  public long lockDate; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    USER_ID((short)2, "userId"),
    REMARK_NAME((short)3, "remarkName"),
    GROUP_ID((short)4, "groupId"),
    TYPE((short)5, "type"),
    JOIN_TIME((short)6, "joinTime"),
    IS_LOCK((short)7, "isLock"),
    LOCK_DATE((short)8, "lockDate");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // USER_ID
          return USER_ID;
        case 3: // REMARK_NAME
          return REMARK_NAME;
        case 4: // GROUP_ID
          return GROUP_ID;
        case 5: // TYPE
          return TYPE;
        case 6: // JOIN_TIME
          return JOIN_TIME;
        case 7: // IS_LOCK
          return IS_LOCK;
        case 8: // LOCK_DATE
          return LOCK_DATE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private static final int __USERID_ISSET_ID = 1;
  private static final int __GROUPID_ISSET_ID = 2;
  private static final int __TYPE_ISSET_ID = 3;
  private static final int __JOINTIME_ISSET_ID = 4;
  private static final int __ISLOCK_ISSET_ID = 5;
  private static final int __LOCKDATE_ISSET_ID = 6;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.USER_ID, new org.apache.thrift.meta_data.FieldMetaData("userId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.REMARK_NAME, new org.apache.thrift.meta_data.FieldMetaData("remarkName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.GROUP_ID, new org.apache.thrift.meta_data.FieldMetaData("groupId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.JOIN_TIME, new org.apache.thrift.meta_data.FieldMetaData("joinTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.IS_LOCK, new org.apache.thrift.meta_data.FieldMetaData("isLock", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.LOCK_DATE, new org.apache.thrift.meta_data.FieldMetaData("lockDate", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GroupMember.class, metaDataMap);
  }

  public GroupMember() {
  }

  public GroupMember(
    long id,
    long userId,
    java.lang.String remarkName,
    long groupId,
    int type,
    long joinTime,
    int isLock,
    long lockDate)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.userId = userId;
    setUserIdIsSet(true);
    this.remarkName = remarkName;
    this.groupId = groupId;
    setGroupIdIsSet(true);
    this.type = type;
    setTypeIsSet(true);
    this.joinTime = joinTime;
    setJoinTimeIsSet(true);
    this.isLock = isLock;
    setIsLockIsSet(true);
    this.lockDate = lockDate;
    setLockDateIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GroupMember(GroupMember other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    this.userId = other.userId;
    if (other.isSetRemarkName()) {
      this.remarkName = other.remarkName;
    }
    this.groupId = other.groupId;
    this.type = other.type;
    this.joinTime = other.joinTime;
    this.isLock = other.isLock;
    this.lockDate = other.lockDate;
  }

  public GroupMember deepCopy() {
    return new GroupMember(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    setUserIdIsSet(false);
    this.userId = 0;
    this.remarkName = null;
    setGroupIdIsSet(false);
    this.groupId = 0;
    setTypeIsSet(false);
    this.type = 0;
    setJoinTimeIsSet(false);
    this.joinTime = 0;
    setIsLockIsSet(false);
    this.isLock = 0;
    setLockDateIsSet(false);
    this.lockDate = 0;
  }

  public long getId() {
    return this.id;
  }

  public GroupMember setId(long id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public long getUserId() {
    return this.userId;
  }

  public GroupMember setUserId(long userId) {
    this.userId = userId;
    setUserIdIsSet(true);
    return this;
  }

  public void unsetUserId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __USERID_ISSET_ID);
  }

  /** Returns true if field userId is set (has been assigned a value) and false otherwise */
  public boolean isSetUserId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __USERID_ISSET_ID);
  }

  public void setUserIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __USERID_ISSET_ID, value);
  }

  public java.lang.String getRemarkName() {
    return this.remarkName;
  }

  public GroupMember setRemarkName(java.lang.String remarkName) {
    this.remarkName = remarkName;
    return this;
  }

  public void unsetRemarkName() {
    this.remarkName = null;
  }

  /** Returns true if field remarkName is set (has been assigned a value) and false otherwise */
  public boolean isSetRemarkName() {
    return this.remarkName != null;
  }

  public void setRemarkNameIsSet(boolean value) {
    if (!value) {
      this.remarkName = null;
    }
  }

  public long getGroupId() {
    return this.groupId;
  }

  public GroupMember setGroupId(long groupId) {
    this.groupId = groupId;
    setGroupIdIsSet(true);
    return this;
  }

  public void unsetGroupId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __GROUPID_ISSET_ID);
  }

  /** Returns true if field groupId is set (has been assigned a value) and false otherwise */
  public boolean isSetGroupId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __GROUPID_ISSET_ID);
  }

  public void setGroupIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __GROUPID_ISSET_ID, value);
  }

  public int getType() {
    return this.type;
  }

  public GroupMember setType(int type) {
    this.type = type;
    setTypeIsSet(true);
    return this;
  }

  public void unsetType() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TYPE_ISSET_ID);
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TYPE_ISSET_ID);
  }

  public void setTypeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TYPE_ISSET_ID, value);
  }

  public long getJoinTime() {
    return this.joinTime;
  }

  public GroupMember setJoinTime(long joinTime) {
    this.joinTime = joinTime;
    setJoinTimeIsSet(true);
    return this;
  }

  public void unsetJoinTime() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __JOINTIME_ISSET_ID);
  }

  /** Returns true if field joinTime is set (has been assigned a value) and false otherwise */
  public boolean isSetJoinTime() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __JOINTIME_ISSET_ID);
  }

  public void setJoinTimeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __JOINTIME_ISSET_ID, value);
  }

  public int getIsLock() {
    return this.isLock;
  }

  public GroupMember setIsLock(int isLock) {
    this.isLock = isLock;
    setIsLockIsSet(true);
    return this;
  }

  public void unsetIsLock() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ISLOCK_ISSET_ID);
  }

  /** Returns true if field isLock is set (has been assigned a value) and false otherwise */
  public boolean isSetIsLock() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ISLOCK_ISSET_ID);
  }

  public void setIsLockIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ISLOCK_ISSET_ID, value);
  }

  public long getLockDate() {
    return this.lockDate;
  }

  public GroupMember setLockDate(long lockDate) {
    this.lockDate = lockDate;
    setLockDateIsSet(true);
    return this;
  }

  public void unsetLockDate() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LOCKDATE_ISSET_ID);
  }

  /** Returns true if field lockDate is set (has been assigned a value) and false otherwise */
  public boolean isSetLockDate() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LOCKDATE_ISSET_ID);
  }

  public void setLockDateIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LOCKDATE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((java.lang.Long)value);
      }
      break;

    case USER_ID:
      if (value == null) {
        unsetUserId();
      } else {
        setUserId((java.lang.Long)value);
      }
      break;

    case REMARK_NAME:
      if (value == null) {
        unsetRemarkName();
      } else {
        setRemarkName((java.lang.String)value);
      }
      break;

    case GROUP_ID:
      if (value == null) {
        unsetGroupId();
      } else {
        setGroupId((java.lang.Long)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((java.lang.Integer)value);
      }
      break;

    case JOIN_TIME:
      if (value == null) {
        unsetJoinTime();
      } else {
        setJoinTime((java.lang.Long)value);
      }
      break;

    case IS_LOCK:
      if (value == null) {
        unsetIsLock();
      } else {
        setIsLock((java.lang.Integer)value);
      }
      break;

    case LOCK_DATE:
      if (value == null) {
        unsetLockDate();
      } else {
        setLockDate((java.lang.Long)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case USER_ID:
      return getUserId();

    case REMARK_NAME:
      return getRemarkName();

    case GROUP_ID:
      return getGroupId();

    case TYPE:
      return getType();

    case JOIN_TIME:
      return getJoinTime();

    case IS_LOCK:
      return getIsLock();

    case LOCK_DATE:
      return getLockDate();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case USER_ID:
      return isSetUserId();
    case REMARK_NAME:
      return isSetRemarkName();
    case GROUP_ID:
      return isSetGroupId();
    case TYPE:
      return isSetType();
    case JOIN_TIME:
      return isSetJoinTime();
    case IS_LOCK:
      return isSetIsLock();
    case LOCK_DATE:
      return isSetLockDate();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof GroupMember)
      return this.equals((GroupMember)that);
    return false;
  }

  public boolean equals(GroupMember that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_userId = true;
    boolean that_present_userId = true;
    if (this_present_userId || that_present_userId) {
      if (!(this_present_userId && that_present_userId))
        return false;
      if (this.userId != that.userId)
        return false;
    }

    boolean this_present_remarkName = true && this.isSetRemarkName();
    boolean that_present_remarkName = true && that.isSetRemarkName();
    if (this_present_remarkName || that_present_remarkName) {
      if (!(this_present_remarkName && that_present_remarkName))
        return false;
      if (!this.remarkName.equals(that.remarkName))
        return false;
    }

    boolean this_present_groupId = true;
    boolean that_present_groupId = true;
    if (this_present_groupId || that_present_groupId) {
      if (!(this_present_groupId && that_present_groupId))
        return false;
      if (this.groupId != that.groupId)
        return false;
    }

    boolean this_present_type = true;
    boolean that_present_type = true;
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (this.type != that.type)
        return false;
    }

    boolean this_present_joinTime = true;
    boolean that_present_joinTime = true;
    if (this_present_joinTime || that_present_joinTime) {
      if (!(this_present_joinTime && that_present_joinTime))
        return false;
      if (this.joinTime != that.joinTime)
        return false;
    }

    boolean this_present_isLock = true;
    boolean that_present_isLock = true;
    if (this_present_isLock || that_present_isLock) {
      if (!(this_present_isLock && that_present_isLock))
        return false;
      if (this.isLock != that.isLock)
        return false;
    }

    boolean this_present_lockDate = true;
    boolean that_present_lockDate = true;
    if (this_present_lockDate || that_present_lockDate) {
      if (!(this_present_lockDate && that_present_lockDate))
        return false;
      if (this.lockDate != that.lockDate)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(id);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(userId);

    hashCode = hashCode * 8191 + ((isSetRemarkName()) ? 131071 : 524287);
    if (isSetRemarkName())
      hashCode = hashCode * 8191 + remarkName.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(groupId);

    hashCode = hashCode * 8191 + type;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(joinTime);

    hashCode = hashCode * 8191 + isLock;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(lockDate);

    return hashCode;
  }

  @Override
  public int compareTo(GroupMember other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUserId()).compareTo(other.isSetUserId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userId, other.userId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRemarkName()).compareTo(other.isSetRemarkName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRemarkName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.remarkName, other.remarkName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetGroupId()).compareTo(other.isSetGroupId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGroupId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.groupId, other.groupId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetJoinTime()).compareTo(other.isSetJoinTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetJoinTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.joinTime, other.joinTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetIsLock()).compareTo(other.isSetIsLock());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsLock()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isLock, other.isLock);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetLockDate()).compareTo(other.isSetLockDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLockDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lockDate, other.lockDate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("GroupMember(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("userId:");
    sb.append(this.userId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("remarkName:");
    if (this.remarkName == null) {
      sb.append("null");
    } else {
      sb.append(this.remarkName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("groupId:");
    sb.append(this.groupId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("type:");
    sb.append(this.type);
    first = false;
    if (!first) sb.append(", ");
    sb.append("joinTime:");
    sb.append(this.joinTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("isLock:");
    sb.append(this.isLock);
    first = false;
    if (!first) sb.append(", ");
    sb.append("lockDate:");
    sb.append(this.lockDate);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class GroupMemberStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public GroupMemberStandardScheme getScheme() {
      return new GroupMemberStandardScheme();
    }
  }

  private static class GroupMemberStandardScheme extends org.apache.thrift.scheme.StandardScheme<GroupMember> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GroupMember struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.id = iprot.readI64();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // USER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.userId = iprot.readI64();
              struct.setUserIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // REMARK_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.remarkName = iprot.readString();
              struct.setRemarkNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // GROUP_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.groupId = iprot.readI64();
              struct.setGroupIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.type = iprot.readI32();
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // JOIN_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.joinTime = iprot.readI64();
              struct.setJoinTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // IS_LOCK
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.isLock = iprot.readI32();
              struct.setIsLockIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // LOCK_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.lockDate = iprot.readI64();
              struct.setLockDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, GroupMember struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI64(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(USER_ID_FIELD_DESC);
      oprot.writeI64(struct.userId);
      oprot.writeFieldEnd();
      if (struct.remarkName != null) {
        oprot.writeFieldBegin(REMARK_NAME_FIELD_DESC);
        oprot.writeString(struct.remarkName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(GROUP_ID_FIELD_DESC);
      oprot.writeI64(struct.groupId);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TYPE_FIELD_DESC);
      oprot.writeI32(struct.type);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(JOIN_TIME_FIELD_DESC);
      oprot.writeI64(struct.joinTime);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(IS_LOCK_FIELD_DESC);
      oprot.writeI32(struct.isLock);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LOCK_DATE_FIELD_DESC);
      oprot.writeI64(struct.lockDate);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GroupMemberTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public GroupMemberTupleScheme getScheme() {
      return new GroupMemberTupleScheme();
    }
  }

  private static class GroupMemberTupleScheme extends org.apache.thrift.scheme.TupleScheme<GroupMember> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GroupMember struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetUserId()) {
        optionals.set(1);
      }
      if (struct.isSetRemarkName()) {
        optionals.set(2);
      }
      if (struct.isSetGroupId()) {
        optionals.set(3);
      }
      if (struct.isSetType()) {
        optionals.set(4);
      }
      if (struct.isSetJoinTime()) {
        optionals.set(5);
      }
      if (struct.isSetIsLock()) {
        optionals.set(6);
      }
      if (struct.isSetLockDate()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetId()) {
        oprot.writeI64(struct.id);
      }
      if (struct.isSetUserId()) {
        oprot.writeI64(struct.userId);
      }
      if (struct.isSetRemarkName()) {
        oprot.writeString(struct.remarkName);
      }
      if (struct.isSetGroupId()) {
        oprot.writeI64(struct.groupId);
      }
      if (struct.isSetType()) {
        oprot.writeI32(struct.type);
      }
      if (struct.isSetJoinTime()) {
        oprot.writeI64(struct.joinTime);
      }
      if (struct.isSetIsLock()) {
        oprot.writeI32(struct.isLock);
      }
      if (struct.isSetLockDate()) {
        oprot.writeI64(struct.lockDate);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GroupMember struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.id = iprot.readI64();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.userId = iprot.readI64();
        struct.setUserIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.remarkName = iprot.readString();
        struct.setRemarkNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.groupId = iprot.readI64();
        struct.setGroupIdIsSet(true);
      }
      if (incoming.get(4)) {
        struct.type = iprot.readI32();
        struct.setTypeIsSet(true);
      }
      if (incoming.get(5)) {
        struct.joinTime = iprot.readI64();
        struct.setJoinTimeIsSet(true);
      }
      if (incoming.get(6)) {
        struct.isLock = iprot.readI32();
        struct.setIsLockIsSet(true);
      }
      if (incoming.get(7)) {
        struct.lockDate = iprot.readI64();
        struct.setLockDateIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
