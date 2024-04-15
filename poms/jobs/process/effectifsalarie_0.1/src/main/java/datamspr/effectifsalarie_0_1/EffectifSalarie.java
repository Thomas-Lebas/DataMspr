// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package datamspr.effectifsalarie_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: EffectifSalarie Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class EffectifSalarie implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "EffectifSalarie";
	private final String projectName = "DATAMSPR";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					EffectifSalarie.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(EffectifSalarie.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tReplicate_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_EffectifSalarie = new byte[0];
		static byte[] commonByteArray_DATAMSPR_EffectifSalarie = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public String LibelleZoneEmploi;

		public String getLibelleZoneEmploi() {
			return this.LibelleZoneEmploi;
		}

		public String ZoneEmploi;

		public String getZoneEmploi() {
			return this.ZoneEmploi;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public Integer Annee;

		public Integer getAnnee() {
			return this.Annee;
		}

		public Integer Trimestre;

		public Integer getTrimestre() {
			return this.Trimestre;
		}

		public java.util.Date DernierJourTrimestre;

		public java.util.Date getDernierJourTrimestre() {
			return this.DernierJourTrimestre;
		}

		public Integer EffectifSalarieBrut;

		public Integer getEffectifSalarieBrut() {
			return this.EffectifSalarieBrut;
		}

		public Integer EffectifSalarieCvs;

		public Integer getEffectifSalarieCvs() {
			return this.EffectifSalarieCvs;
		}

		public Long MasseSalarialeBrut;

		public Long getMasseSalarialeBrut() {
			return this.MasseSalarialeBrut;
		}

		public Long MasseSalarialeCvs;

		public Long getMasseSalarialeCvs() {
			return this.MasseSalarialeCvs;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.Id;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row4Struct other = (row4Struct) obj;

			if (this.Id != other.Id)
				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.Id = this.Id;
			other.LibelleZoneEmploi = this.LibelleZoneEmploi;
			other.ZoneEmploi = this.ZoneEmploi;
			other.Region = this.Region;
			other.Annee = this.Annee;
			other.Trimestre = this.Trimestre;
			other.DernierJourTrimestre = this.DernierJourTrimestre;
			other.EffectifSalarieBrut = this.EffectifSalarieBrut;
			other.EffectifSalarieCvs = this.EffectifSalarieCvs;
			other.MasseSalarialeBrut = this.MasseSalarialeBrut;
			other.MasseSalarialeCvs = this.MasseSalarialeCvs;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.Id = this.Id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_EffectifSalarie.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EffectifSalarie.length == 0) {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_EffectifSalarie, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EffectifSalarie, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_EffectifSalarie.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EffectifSalarie.length == 0) {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_EffectifSalarie, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EffectifSalarie, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EffectifSalarie) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.LibelleZoneEmploi = readString(dis);

					this.ZoneEmploi = readString(dis);

					this.Region = readString(dis);

					this.Annee = readInteger(dis);

					this.Trimestre = readInteger(dis);

					this.DernierJourTrimestre = readDate(dis);

					this.EffectifSalarieBrut = readInteger(dis);

					this.EffectifSalarieCvs = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeBrut = null;
					} else {
						this.MasseSalarialeBrut = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeCvs = null;
					} else {
						this.MasseSalarialeCvs = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EffectifSalarie) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.LibelleZoneEmploi = readString(dis);

					this.ZoneEmploi = readString(dis);

					this.Region = readString(dis);

					this.Annee = readInteger(dis);

					this.Trimestre = readInteger(dis);

					this.DernierJourTrimestre = readDate(dis);

					this.EffectifSalarieBrut = readInteger(dis);

					this.EffectifSalarieCvs = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeBrut = null;
					} else {
						this.MasseSalarialeBrut = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeCvs = null;
					} else {
						this.MasseSalarialeCvs = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.LibelleZoneEmploi, dos);

				// String

				writeString(this.ZoneEmploi, dos);

				// String

				writeString(this.Region, dos);

				// Integer

				writeInteger(this.Annee, dos);

				// Integer

				writeInteger(this.Trimestre, dos);

				// java.util.Date

				writeDate(this.DernierJourTrimestre, dos);

				// Integer

				writeInteger(this.EffectifSalarieBrut, dos);

				// Integer

				writeInteger(this.EffectifSalarieCvs, dos);

				// Long

				if (this.MasseSalarialeBrut == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeBrut);
				}

				// Long

				if (this.MasseSalarialeCvs == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeCvs);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.LibelleZoneEmploi, dos);

				// String

				writeString(this.ZoneEmploi, dos);

				// String

				writeString(this.Region, dos);

				// Integer

				writeInteger(this.Annee, dos);

				// Integer

				writeInteger(this.Trimestre, dos);

				// java.util.Date

				writeDate(this.DernierJourTrimestre, dos);

				// Integer

				writeInteger(this.EffectifSalarieBrut, dos);

				// Integer

				writeInteger(this.EffectifSalarieCvs, dos);

				// Long

				if (this.MasseSalarialeBrut == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeBrut);
				}

				// Long

				if (this.MasseSalarialeCvs == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeCvs);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",LibelleZoneEmploi=" + LibelleZoneEmploi);
			sb.append(",ZoneEmploi=" + ZoneEmploi);
			sb.append(",Region=" + Region);
			sb.append(",Annee=" + String.valueOf(Annee));
			sb.append(",Trimestre=" + String.valueOf(Trimestre));
			sb.append(",DernierJourTrimestre=" + String.valueOf(DernierJourTrimestre));
			sb.append(",EffectifSalarieBrut=" + String.valueOf(EffectifSalarieBrut));
			sb.append(",EffectifSalarieCvs=" + String.valueOf(EffectifSalarieCvs));
			sb.append(",MasseSalarialeBrut=" + String.valueOf(MasseSalarialeBrut));
			sb.append(",MasseSalarialeCvs=" + String.valueOf(MasseSalarialeCvs));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Id, other.Id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_EffectifSalarie = new byte[0];
		static byte[] commonByteArray_DATAMSPR_EffectifSalarie = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public String LibelleZoneEmploi;

		public String getLibelleZoneEmploi() {
			return this.LibelleZoneEmploi;
		}

		public String ZoneEmploi;

		public String getZoneEmploi() {
			return this.ZoneEmploi;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public Integer Annee;

		public Integer getAnnee() {
			return this.Annee;
		}

		public Integer Trimestre;

		public Integer getTrimestre() {
			return this.Trimestre;
		}

		public java.util.Date DernierJourTrimestre;

		public java.util.Date getDernierJourTrimestre() {
			return this.DernierJourTrimestre;
		}

		public Integer EffectifSalarieBrut;

		public Integer getEffectifSalarieBrut() {
			return this.EffectifSalarieBrut;
		}

		public Integer EffectifSalarieCvs;

		public Integer getEffectifSalarieCvs() {
			return this.EffectifSalarieCvs;
		}

		public Long MasseSalarialeBrut;

		public Long getMasseSalarialeBrut() {
			return this.MasseSalarialeBrut;
		}

		public Long MasseSalarialeCvs;

		public Long getMasseSalarialeCvs() {
			return this.MasseSalarialeCvs;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.Id;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row3Struct other = (row3Struct) obj;

			if (this.Id != other.Id)
				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.Id = this.Id;
			other.LibelleZoneEmploi = this.LibelleZoneEmploi;
			other.ZoneEmploi = this.ZoneEmploi;
			other.Region = this.Region;
			other.Annee = this.Annee;
			other.Trimestre = this.Trimestre;
			other.DernierJourTrimestre = this.DernierJourTrimestre;
			other.EffectifSalarieBrut = this.EffectifSalarieBrut;
			other.EffectifSalarieCvs = this.EffectifSalarieCvs;
			other.MasseSalarialeBrut = this.MasseSalarialeBrut;
			other.MasseSalarialeCvs = this.MasseSalarialeCvs;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.Id = this.Id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_EffectifSalarie.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EffectifSalarie.length == 0) {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_EffectifSalarie, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EffectifSalarie, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_EffectifSalarie.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EffectifSalarie.length == 0) {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_EffectifSalarie, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EffectifSalarie, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EffectifSalarie) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.LibelleZoneEmploi = readString(dis);

					this.ZoneEmploi = readString(dis);

					this.Region = readString(dis);

					this.Annee = readInteger(dis);

					this.Trimestre = readInteger(dis);

					this.DernierJourTrimestre = readDate(dis);

					this.EffectifSalarieBrut = readInteger(dis);

					this.EffectifSalarieCvs = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeBrut = null;
					} else {
						this.MasseSalarialeBrut = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeCvs = null;
					} else {
						this.MasseSalarialeCvs = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EffectifSalarie) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.LibelleZoneEmploi = readString(dis);

					this.ZoneEmploi = readString(dis);

					this.Region = readString(dis);

					this.Annee = readInteger(dis);

					this.Trimestre = readInteger(dis);

					this.DernierJourTrimestre = readDate(dis);

					this.EffectifSalarieBrut = readInteger(dis);

					this.EffectifSalarieCvs = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeBrut = null;
					} else {
						this.MasseSalarialeBrut = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeCvs = null;
					} else {
						this.MasseSalarialeCvs = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.LibelleZoneEmploi, dos);

				// String

				writeString(this.ZoneEmploi, dos);

				// String

				writeString(this.Region, dos);

				// Integer

				writeInteger(this.Annee, dos);

				// Integer

				writeInteger(this.Trimestre, dos);

				// java.util.Date

				writeDate(this.DernierJourTrimestre, dos);

				// Integer

				writeInteger(this.EffectifSalarieBrut, dos);

				// Integer

				writeInteger(this.EffectifSalarieCvs, dos);

				// Long

				if (this.MasseSalarialeBrut == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeBrut);
				}

				// Long

				if (this.MasseSalarialeCvs == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeCvs);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.LibelleZoneEmploi, dos);

				// String

				writeString(this.ZoneEmploi, dos);

				// String

				writeString(this.Region, dos);

				// Integer

				writeInteger(this.Annee, dos);

				// Integer

				writeInteger(this.Trimestre, dos);

				// java.util.Date

				writeDate(this.DernierJourTrimestre, dos);

				// Integer

				writeInteger(this.EffectifSalarieBrut, dos);

				// Integer

				writeInteger(this.EffectifSalarieCvs, dos);

				// Long

				if (this.MasseSalarialeBrut == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeBrut);
				}

				// Long

				if (this.MasseSalarialeCvs == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeCvs);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",LibelleZoneEmploi=" + LibelleZoneEmploi);
			sb.append(",ZoneEmploi=" + ZoneEmploi);
			sb.append(",Region=" + Region);
			sb.append(",Annee=" + String.valueOf(Annee));
			sb.append(",Trimestre=" + String.valueOf(Trimestre));
			sb.append(",DernierJourTrimestre=" + String.valueOf(DernierJourTrimestre));
			sb.append(",EffectifSalarieBrut=" + String.valueOf(EffectifSalarieBrut));
			sb.append(",EffectifSalarieCvs=" + String.valueOf(EffectifSalarieCvs));
			sb.append(",MasseSalarialeBrut=" + String.valueOf(MasseSalarialeBrut));
			sb.append(",MasseSalarialeCvs=" + String.valueOf(MasseSalarialeCvs));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Id, other.Id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_EffectifSalarie = new byte[0];
		static byte[] commonByteArray_DATAMSPR_EffectifSalarie = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public String LibelleZoneEmploi;

		public String getLibelleZoneEmploi() {
			return this.LibelleZoneEmploi;
		}

		public String ZoneEmploi;

		public String getZoneEmploi() {
			return this.ZoneEmploi;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public Integer Annee;

		public Integer getAnnee() {
			return this.Annee;
		}

		public Integer Trimestre;

		public Integer getTrimestre() {
			return this.Trimestre;
		}

		public java.util.Date DernierJourTrimestre;

		public java.util.Date getDernierJourTrimestre() {
			return this.DernierJourTrimestre;
		}

		public Integer EffectifSalarieBrut;

		public Integer getEffectifSalarieBrut() {
			return this.EffectifSalarieBrut;
		}

		public Integer EffectifSalarieCvs;

		public Integer getEffectifSalarieCvs() {
			return this.EffectifSalarieCvs;
		}

		public Long MasseSalarialeBrut;

		public Long getMasseSalarialeBrut() {
			return this.MasseSalarialeBrut;
		}

		public Long MasseSalarialeCvs;

		public Long getMasseSalarialeCvs() {
			return this.MasseSalarialeCvs;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.Id;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row2Struct other = (row2Struct) obj;

			if (this.Id != other.Id)
				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.Id = this.Id;
			other.LibelleZoneEmploi = this.LibelleZoneEmploi;
			other.ZoneEmploi = this.ZoneEmploi;
			other.Region = this.Region;
			other.Annee = this.Annee;
			other.Trimestre = this.Trimestre;
			other.DernierJourTrimestre = this.DernierJourTrimestre;
			other.EffectifSalarieBrut = this.EffectifSalarieBrut;
			other.EffectifSalarieCvs = this.EffectifSalarieCvs;
			other.MasseSalarialeBrut = this.MasseSalarialeBrut;
			other.MasseSalarialeCvs = this.MasseSalarialeCvs;

		}

		public void copyKeysDataTo(row2Struct other) {

			other.Id = this.Id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_EffectifSalarie.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EffectifSalarie.length == 0) {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_EffectifSalarie, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EffectifSalarie, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_EffectifSalarie.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EffectifSalarie.length == 0) {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_EffectifSalarie, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EffectifSalarie, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EffectifSalarie) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.LibelleZoneEmploi = readString(dis);

					this.ZoneEmploi = readString(dis);

					this.Region = readString(dis);

					this.Annee = readInteger(dis);

					this.Trimestre = readInteger(dis);

					this.DernierJourTrimestre = readDate(dis);

					this.EffectifSalarieBrut = readInteger(dis);

					this.EffectifSalarieCvs = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeBrut = null;
					} else {
						this.MasseSalarialeBrut = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeCvs = null;
					} else {
						this.MasseSalarialeCvs = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EffectifSalarie) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.LibelleZoneEmploi = readString(dis);

					this.ZoneEmploi = readString(dis);

					this.Region = readString(dis);

					this.Annee = readInteger(dis);

					this.Trimestre = readInteger(dis);

					this.DernierJourTrimestre = readDate(dis);

					this.EffectifSalarieBrut = readInteger(dis);

					this.EffectifSalarieCvs = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeBrut = null;
					} else {
						this.MasseSalarialeBrut = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeCvs = null;
					} else {
						this.MasseSalarialeCvs = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.LibelleZoneEmploi, dos);

				// String

				writeString(this.ZoneEmploi, dos);

				// String

				writeString(this.Region, dos);

				// Integer

				writeInteger(this.Annee, dos);

				// Integer

				writeInteger(this.Trimestre, dos);

				// java.util.Date

				writeDate(this.DernierJourTrimestre, dos);

				// Integer

				writeInteger(this.EffectifSalarieBrut, dos);

				// Integer

				writeInteger(this.EffectifSalarieCvs, dos);

				// Long

				if (this.MasseSalarialeBrut == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeBrut);
				}

				// Long

				if (this.MasseSalarialeCvs == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeCvs);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.LibelleZoneEmploi, dos);

				// String

				writeString(this.ZoneEmploi, dos);

				// String

				writeString(this.Region, dos);

				// Integer

				writeInteger(this.Annee, dos);

				// Integer

				writeInteger(this.Trimestre, dos);

				// java.util.Date

				writeDate(this.DernierJourTrimestre, dos);

				// Integer

				writeInteger(this.EffectifSalarieBrut, dos);

				// Integer

				writeInteger(this.EffectifSalarieCvs, dos);

				// Long

				if (this.MasseSalarialeBrut == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeBrut);
				}

				// Long

				if (this.MasseSalarialeCvs == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeCvs);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",LibelleZoneEmploi=" + LibelleZoneEmploi);
			sb.append(",ZoneEmploi=" + ZoneEmploi);
			sb.append(",Region=" + Region);
			sb.append(",Annee=" + String.valueOf(Annee));
			sb.append(",Trimestre=" + String.valueOf(Trimestre));
			sb.append(",DernierJourTrimestre=" + String.valueOf(DernierJourTrimestre));
			sb.append(",EffectifSalarieBrut=" + String.valueOf(EffectifSalarieBrut));
			sb.append(",EffectifSalarieCvs=" + String.valueOf(EffectifSalarieCvs));
			sb.append(",MasseSalarialeBrut=" + String.valueOf(MasseSalarialeBrut));
			sb.append(",MasseSalarialeCvs=" + String.valueOf(MasseSalarialeCvs));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Id, other.Id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_EffectifSalarie = new byte[0];
		static byte[] commonByteArray_DATAMSPR_EffectifSalarie = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public String LibelleZoneEmploi;

		public String getLibelleZoneEmploi() {
			return this.LibelleZoneEmploi;
		}

		public String ZoneEmploi;

		public String getZoneEmploi() {
			return this.ZoneEmploi;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public Integer Annee;

		public Integer getAnnee() {
			return this.Annee;
		}

		public Integer Trimestre;

		public Integer getTrimestre() {
			return this.Trimestre;
		}

		public java.util.Date DernierJourTrimestre;

		public java.util.Date getDernierJourTrimestre() {
			return this.DernierJourTrimestre;
		}

		public Integer EffectifSalarieBrut;

		public Integer getEffectifSalarieBrut() {
			return this.EffectifSalarieBrut;
		}

		public Integer EffectifSalarieCvs;

		public Integer getEffectifSalarieCvs() {
			return this.EffectifSalarieCvs;
		}

		public Long MasseSalarialeBrut;

		public Long getMasseSalarialeBrut() {
			return this.MasseSalarialeBrut;
		}

		public Long MasseSalarialeCvs;

		public Long getMasseSalarialeCvs() {
			return this.MasseSalarialeCvs;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.Id;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final out1Struct other = (out1Struct) obj;

			if (this.Id != other.Id)
				return false;

			return true;
		}

		public void copyDataTo(out1Struct other) {

			other.Id = this.Id;
			other.LibelleZoneEmploi = this.LibelleZoneEmploi;
			other.ZoneEmploi = this.ZoneEmploi;
			other.Region = this.Region;
			other.Annee = this.Annee;
			other.Trimestre = this.Trimestre;
			other.DernierJourTrimestre = this.DernierJourTrimestre;
			other.EffectifSalarieBrut = this.EffectifSalarieBrut;
			other.EffectifSalarieCvs = this.EffectifSalarieCvs;
			other.MasseSalarialeBrut = this.MasseSalarialeBrut;
			other.MasseSalarialeCvs = this.MasseSalarialeCvs;

		}

		public void copyKeysDataTo(out1Struct other) {

			other.Id = this.Id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_EffectifSalarie.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EffectifSalarie.length == 0) {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_EffectifSalarie, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EffectifSalarie, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_EffectifSalarie.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EffectifSalarie.length == 0) {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_EffectifSalarie, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EffectifSalarie, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EffectifSalarie) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.LibelleZoneEmploi = readString(dis);

					this.ZoneEmploi = readString(dis);

					this.Region = readString(dis);

					this.Annee = readInteger(dis);

					this.Trimestre = readInteger(dis);

					this.DernierJourTrimestre = readDate(dis);

					this.EffectifSalarieBrut = readInteger(dis);

					this.EffectifSalarieCvs = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeBrut = null;
					} else {
						this.MasseSalarialeBrut = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeCvs = null;
					} else {
						this.MasseSalarialeCvs = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EffectifSalarie) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.LibelleZoneEmploi = readString(dis);

					this.ZoneEmploi = readString(dis);

					this.Region = readString(dis);

					this.Annee = readInteger(dis);

					this.Trimestre = readInteger(dis);

					this.DernierJourTrimestre = readDate(dis);

					this.EffectifSalarieBrut = readInteger(dis);

					this.EffectifSalarieCvs = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeBrut = null;
					} else {
						this.MasseSalarialeBrut = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeCvs = null;
					} else {
						this.MasseSalarialeCvs = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.LibelleZoneEmploi, dos);

				// String

				writeString(this.ZoneEmploi, dos);

				// String

				writeString(this.Region, dos);

				// Integer

				writeInteger(this.Annee, dos);

				// Integer

				writeInteger(this.Trimestre, dos);

				// java.util.Date

				writeDate(this.DernierJourTrimestre, dos);

				// Integer

				writeInteger(this.EffectifSalarieBrut, dos);

				// Integer

				writeInteger(this.EffectifSalarieCvs, dos);

				// Long

				if (this.MasseSalarialeBrut == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeBrut);
				}

				// Long

				if (this.MasseSalarialeCvs == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeCvs);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.LibelleZoneEmploi, dos);

				// String

				writeString(this.ZoneEmploi, dos);

				// String

				writeString(this.Region, dos);

				// Integer

				writeInteger(this.Annee, dos);

				// Integer

				writeInteger(this.Trimestre, dos);

				// java.util.Date

				writeDate(this.DernierJourTrimestre, dos);

				// Integer

				writeInteger(this.EffectifSalarieBrut, dos);

				// Integer

				writeInteger(this.EffectifSalarieCvs, dos);

				// Long

				if (this.MasseSalarialeBrut == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeBrut);
				}

				// Long

				if (this.MasseSalarialeCvs == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeCvs);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",LibelleZoneEmploi=" + LibelleZoneEmploi);
			sb.append(",ZoneEmploi=" + ZoneEmploi);
			sb.append(",Region=" + Region);
			sb.append(",Annee=" + String.valueOf(Annee));
			sb.append(",Trimestre=" + String.valueOf(Trimestre));
			sb.append(",DernierJourTrimestre=" + String.valueOf(DernierJourTrimestre));
			sb.append(",EffectifSalarieBrut=" + String.valueOf(EffectifSalarieBrut));
			sb.append(",EffectifSalarieCvs=" + String.valueOf(EffectifSalarieCvs));
			sb.append(",MasseSalarialeBrut=" + String.valueOf(MasseSalarialeBrut));
			sb.append(",MasseSalarialeCvs=" + String.valueOf(MasseSalarialeCvs));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out1Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Id, other.Id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_EffectifSalarie = new byte[0];
		static byte[] commonByteArray_DATAMSPR_EffectifSalarie = new byte[0];

		public String LibelleZoneEmploi;

		public String getLibelleZoneEmploi() {
			return this.LibelleZoneEmploi;
		}

		public String ZoneEmploi;

		public String getZoneEmploi() {
			return this.ZoneEmploi;
		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public String AncienneRegion;

		public String getAncienneRegion() {
			return this.AncienneRegion;
		}

		public String LibelleZoneEmploiRegion;

		public String getLibelleZoneEmploiRegion() {
			return this.LibelleZoneEmploiRegion;
		}

		public String LibelleZoneEmploiRegionalisee;

		public String getLibelleZoneEmploiRegionalisee() {
			return this.LibelleZoneEmploiRegionalisee;
		}

		public Integer Annee;

		public Integer getAnnee() {
			return this.Annee;
		}

		public Integer Trimestre;

		public Integer getTrimestre() {
			return this.Trimestre;
		}

		public java.util.Date DernierJourTrimestre;

		public java.util.Date getDernierJourTrimestre() {
			return this.DernierJourTrimestre;
		}

		public Integer CodeRegion;

		public Integer getCodeRegion() {
			return this.CodeRegion;
		}

		public Integer CodeAncienneRegion;

		public Integer getCodeAncienneRegion() {
			return this.CodeAncienneRegion;
		}

		public Integer CodeZoneEmploi;

		public Integer getCodeZoneEmploi() {
			return this.CodeZoneEmploi;
		}

		public Integer CodeZoneEmploiRegionalisee;

		public Integer getCodeZoneEmploiRegionalisee() {
			return this.CodeZoneEmploiRegionalisee;
		}

		public Integer EffectifSalarieBrut;

		public Integer getEffectifSalarieBrut() {
			return this.EffectifSalarieBrut;
		}

		public Integer EffectifSalarieCvs;

		public Integer getEffectifSalarieCvs() {
			return this.EffectifSalarieCvs;
		}

		public Long MasseSalarialeBrut;

		public Long getMasseSalarialeBrut() {
			return this.MasseSalarialeBrut;
		}

		public Long MasseSalarialeCvs;

		public Long getMasseSalarialeCvs() {
			return this.MasseSalarialeCvs;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_EffectifSalarie.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EffectifSalarie.length == 0) {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_EffectifSalarie, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EffectifSalarie, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_EffectifSalarie.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_EffectifSalarie.length == 0) {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_EffectifSalarie = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_EffectifSalarie, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_EffectifSalarie, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EffectifSalarie) {

				try {

					int length = 0;

					this.LibelleZoneEmploi = readString(dis);

					this.ZoneEmploi = readString(dis);

					this.Region = readString(dis);

					this.AncienneRegion = readString(dis);

					this.LibelleZoneEmploiRegion = readString(dis);

					this.LibelleZoneEmploiRegionalisee = readString(dis);

					this.Annee = readInteger(dis);

					this.Trimestre = readInteger(dis);

					this.DernierJourTrimestre = readDate(dis);

					this.CodeRegion = readInteger(dis);

					this.CodeAncienneRegion = readInteger(dis);

					this.CodeZoneEmploi = readInteger(dis);

					this.CodeZoneEmploiRegionalisee = readInteger(dis);

					this.EffectifSalarieBrut = readInteger(dis);

					this.EffectifSalarieCvs = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeBrut = null;
					} else {
						this.MasseSalarialeBrut = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeCvs = null;
					} else {
						this.MasseSalarialeCvs = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_EffectifSalarie) {

				try {

					int length = 0;

					this.LibelleZoneEmploi = readString(dis);

					this.ZoneEmploi = readString(dis);

					this.Region = readString(dis);

					this.AncienneRegion = readString(dis);

					this.LibelleZoneEmploiRegion = readString(dis);

					this.LibelleZoneEmploiRegionalisee = readString(dis);

					this.Annee = readInteger(dis);

					this.Trimestre = readInteger(dis);

					this.DernierJourTrimestre = readDate(dis);

					this.CodeRegion = readInteger(dis);

					this.CodeAncienneRegion = readInteger(dis);

					this.CodeZoneEmploi = readInteger(dis);

					this.CodeZoneEmploiRegionalisee = readInteger(dis);

					this.EffectifSalarieBrut = readInteger(dis);

					this.EffectifSalarieCvs = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeBrut = null;
					} else {
						this.MasseSalarialeBrut = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MasseSalarialeCvs = null;
					} else {
						this.MasseSalarialeCvs = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.LibelleZoneEmploi, dos);

				// String

				writeString(this.ZoneEmploi, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.AncienneRegion, dos);

				// String

				writeString(this.LibelleZoneEmploiRegion, dos);

				// String

				writeString(this.LibelleZoneEmploiRegionalisee, dos);

				// Integer

				writeInteger(this.Annee, dos);

				// Integer

				writeInteger(this.Trimestre, dos);

				// java.util.Date

				writeDate(this.DernierJourTrimestre, dos);

				// Integer

				writeInteger(this.CodeRegion, dos);

				// Integer

				writeInteger(this.CodeAncienneRegion, dos);

				// Integer

				writeInteger(this.CodeZoneEmploi, dos);

				// Integer

				writeInteger(this.CodeZoneEmploiRegionalisee, dos);

				// Integer

				writeInteger(this.EffectifSalarieBrut, dos);

				// Integer

				writeInteger(this.EffectifSalarieCvs, dos);

				// Long

				if (this.MasseSalarialeBrut == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeBrut);
				}

				// Long

				if (this.MasseSalarialeCvs == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeCvs);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.LibelleZoneEmploi, dos);

				// String

				writeString(this.ZoneEmploi, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.AncienneRegion, dos);

				// String

				writeString(this.LibelleZoneEmploiRegion, dos);

				// String

				writeString(this.LibelleZoneEmploiRegionalisee, dos);

				// Integer

				writeInteger(this.Annee, dos);

				// Integer

				writeInteger(this.Trimestre, dos);

				// java.util.Date

				writeDate(this.DernierJourTrimestre, dos);

				// Integer

				writeInteger(this.CodeRegion, dos);

				// Integer

				writeInteger(this.CodeAncienneRegion, dos);

				// Integer

				writeInteger(this.CodeZoneEmploi, dos);

				// Integer

				writeInteger(this.CodeZoneEmploiRegionalisee, dos);

				// Integer

				writeInteger(this.EffectifSalarieBrut, dos);

				// Integer

				writeInteger(this.EffectifSalarieCvs, dos);

				// Long

				if (this.MasseSalarialeBrut == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeBrut);
				}

				// Long

				if (this.MasseSalarialeCvs == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.MasseSalarialeCvs);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("LibelleZoneEmploi=" + LibelleZoneEmploi);
			sb.append(",ZoneEmploi=" + ZoneEmploi);
			sb.append(",Region=" + Region);
			sb.append(",AncienneRegion=" + AncienneRegion);
			sb.append(",LibelleZoneEmploiRegion=" + LibelleZoneEmploiRegion);
			sb.append(",LibelleZoneEmploiRegionalisee=" + LibelleZoneEmploiRegionalisee);
			sb.append(",Annee=" + String.valueOf(Annee));
			sb.append(",Trimestre=" + String.valueOf(Trimestre));
			sb.append(",DernierJourTrimestre=" + String.valueOf(DernierJourTrimestre));
			sb.append(",CodeRegion=" + String.valueOf(CodeRegion));
			sb.append(",CodeAncienneRegion=" + String.valueOf(CodeAncienneRegion));
			sb.append(",CodeZoneEmploi=" + String.valueOf(CodeZoneEmploi));
			sb.append(",CodeZoneEmploiRegionalisee=" + String.valueOf(CodeZoneEmploiRegionalisee));
			sb.append(",EffectifSalarieBrut=" + String.valueOf(EffectifSalarieBrut));
			sb.append(",EffectifSalarieCvs=" + String.valueOf(EffectifSalarieCvs));
			sb.append(",MasseSalarialeBrut=" + String.valueOf(MasseSalarialeBrut));
			sb.append(",MasseSalarialeCvs=" + String.valueOf(MasseSalarialeCvs));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				out1Struct out1 = new out1Struct();
				out1Struct row2 = out1;
				row4Struct row4 = new row4Struct();
				row3Struct row3 = new row3Struct();

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_output/EffectifSalariale.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						dir_tFileOutputDelimited_1.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				if (fileToDelete_tFileOutputDelimited_1.exists()) {
					fileToDelete_tFileOutputDelimited_1.delete();
				}
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "UTF-8"));
				if (filetFileOutputDelimited_1.length() == 0) {
					outtFileOutputDelimited_1.write("Id");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("LibelleZoneEmploi");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("ZoneEmploi");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Region");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Annee");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Trimestre");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("DernierJourTrimestre");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("EffectifSalarieBrut");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("EffectifSalarieCvs");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("MasseSalarialeBrut");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("MasseSalarialeCvs");
					outtFileOutputDelimited_1.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tDBOutput_1 = 0;

				int updateKeyCount_tDBOutput_1 = 1;
				if (updateKeyCount_tDBOutput_1 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_1 == 11 && true) {
					System.err.println("For update, every Schema column can not be a key");
				}

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;

				String tableName_tDBOutput_1 = "EffectifSalariale";
				boolean whetherReject_tDBOutput_1 = false;

				java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
				calendar_tDBOutput_1.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				calendar_tDBOutput_1.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				long date_tDBOutput_1;

				java.sql.Connection conn_tDBOutput_1 = null;

				String properties_tDBOutput_1 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
				if (properties_tDBOutput_1 == null || properties_tDBOutput_1.trim().length() == 0) {
					properties_tDBOutput_1 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_1.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_1 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_1.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_1 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_1 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "datamspr" + "?"
						+ properties_tDBOutput_1;

				String driverClass_tDBOutput_1 = "com.mysql.cj.jdbc.Driver";

				String dbUser_tDBOutput_1 = "root";

				final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:TGSHgj+Z5shBgT34q20bNkSjK/lYsbGViPOAm8ntpGg=");

				String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
				java.lang.Class.forName(driverClass_tDBOutput_1);

				conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
						dbPwd_tDBOutput_1);

				resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
				conn_tDBOutput_1.setAutoCommit(false);
				int commitEvery_tDBOutput_1 = 10000;
				int commitCounter_tDBOutput_1 = 0;

				int count_tDBOutput_1 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_1 = conn_tDBOutput_1.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_1 = dbMetaData_tDBOutput_1.getTables("datamspr", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_1 = false;
				while (rsTable_tDBOutput_1.next()) {
					String table_tDBOutput_1 = rsTable_tDBOutput_1.getString("TABLE_NAME");
					if (table_tDBOutput_1.equalsIgnoreCase("EffectifSalariale")) {
						whetherExist_tDBOutput_1 = true;
						break;
					}
				}
				if (whetherExist_tDBOutput_1) {
					try (java.sql.Statement stmtDrop_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
						stmtDrop_tDBOutput_1.execute("DROP TABLE `" + tableName_tDBOutput_1 + "`");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
					stmtCreate_tDBOutput_1.execute("CREATE TABLE `" + tableName_tDBOutput_1
							+ "`(`Id` INT(255)   not null ,`LibelleZoneEmploi` VARCHAR(255)  ,`ZoneEmploi` VARCHAR(255)  ,`Region` VARCHAR(255)  ,`Annee` INT(255)  ,`Trimestre` INT(255)  ,`DernierJourTrimestre` DATETIME ,`EffectifSalarieBrut` INT(255)  ,`EffectifSalarieCvs` INT(255)  ,`MasseSalarialeBrut` BIGINT(255)  ,`MasseSalarialeCvs` BIGINT(255)  ,primary key(`Id`))");
				}
				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement("SELECT COUNT(1) FROM `" + "EffectifSalariale" + "` WHERE `Id` = ?");
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);
				String insert_tDBOutput_1 = "INSERT INTO `" + "EffectifSalariale"
						+ "` (`Id`,`LibelleZoneEmploi`,`ZoneEmploi`,`Region`,`Annee`,`Trimestre`,`DernierJourTrimestre`,`EffectifSalarieBrut`,`EffectifSalarieCvs`,`MasseSalarialeBrut`,`MasseSalarialeCvs`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

				java.sql.PreparedStatement pstmtInsert_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmtInsert_tDBOutput_1", pstmtInsert_tDBOutput_1);
				String update_tDBOutput_1 = "UPDATE `" + "EffectifSalariale"
						+ "` SET `LibelleZoneEmploi` = ?,`ZoneEmploi` = ?,`Region` = ?,`Annee` = ?,`Trimestre` = ?,`DernierJourTrimestre` = ?,`EffectifSalarieBrut` = ?,`EffectifSalarieCvs` = ?,`MasseSalarialeBrut` = ?,`MasseSalarialeCvs` = ? WHERE `Id` = ?";

				java.sql.PreparedStatement pstmtUpdate_tDBOutput_1 = conn_tDBOutput_1
						.prepareStatement(update_tDBOutput_1);
				resourceMap.put("pstmtUpdate_tDBOutput_1", pstmtUpdate_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tReplicate_1 begin ] start
				 */

				ok_Hash.put("tReplicate_1", false);
				start_Hash.put("tReplicate_1", System.currentTimeMillis());

				currentComponent = "tReplicate_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tReplicate_1 = 0;

				/**
				 * [tReplicate_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out1");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[11];

					public void addRow(String[] row) {

						for (int i = 0; i < 11; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 10 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 10 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|%7$-");
							sbformat.append(colLengths[6]);
							sbformat.append("s");

							sbformat.append("|%8$-");
							sbformat.append(colLengths[7]);
							sbformat.append("s");

							sbformat.append("|%9$-");
							sbformat.append(colLengths[8]);
							sbformat.append("s");

							sbformat.append("|%10$-");
							sbformat.append(colLengths[9]);
							sbformat.append("s");

							sbformat.append("|%11$-");
							sbformat.append(colLengths[10]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[5] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[6] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[7] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[8] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[9] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[10] - fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("tLogRow_1");
				util_tLogRow_1.addRow(new String[] { "Id", "LibelleZoneEmploi", "ZoneEmploi", "Region", "Annee",
						"Trimestre", "DernierJourTrimestre", "EffectifSalarieBrut", "EffectifSalarieCvs",
						"MasseSalarialeBrut", "MasseSalarialeCvs", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
					int Id;
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				out1Struct out1_tmp = new out1Struct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/effectifs-salaries-et-masse-salariale-du-secteur-prive-par-zone-demploi.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/effectifs-salaries-et-masse-salariale-du-secteur-prive-par-zone-demploi.csv",
								"UTF-8", ";", "\n", true, 1, 0, limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row1 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row1 = new row1Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							row1.LibelleZoneEmploi = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 1;

							row1.ZoneEmploi = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 2;

							row1.Region = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row1.AncienneRegion = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row1.LibelleZoneEmploiRegion = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							row1.LibelleZoneEmploiRegionalisee = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 6;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Annee = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Annee", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Annee = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 7;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Trimestre = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Trimestre", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Trimestre = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 8;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.DernierJourTrimestre = ParserUtils.parseTo_Date(temp, "yyyy-MM-dd");

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"DernierJourTrimestre", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.DernierJourTrimestre = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 9;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.CodeRegion = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"CodeRegion", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.CodeRegion = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 10;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.CodeAncienneRegion = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"CodeAncienneRegion", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.CodeAncienneRegion = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 11;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.CodeZoneEmploi = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"CodeZoneEmploi", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.CodeZoneEmploi = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 12;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.CodeZoneEmploiRegionalisee = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"CodeZoneEmploiRegionalisee", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.CodeZoneEmploiRegionalisee = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 13;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.EffectifSalarieBrut = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"EffectifSalarieBrut", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.EffectifSalarieBrut = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 14;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.EffectifSalarieCvs = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"EffectifSalarieCvs", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.EffectifSalarieCvs = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 15;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.MasseSalarialeBrut = ParserUtils.parseTo_Long(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"MasseSalarialeBrut", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.MasseSalarialeBrut = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 16;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.MasseSalarialeCvs = ParserUtils.parseTo_Long(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"MasseSalarialeCvs", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.MasseSalarialeCvs = null;

							}

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row1 = null;

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;
								Var.Id = Numeric.sequence("Id", 1, 1);// ###############################
								// ###############################
								// # Output tables

								out1 = null;

// # Output table : 'out1'
								out1_tmp.Id = Var.Id;
								out1_tmp.LibelleZoneEmploi = row1.LibelleZoneEmploi;
								out1_tmp.ZoneEmploi = row1.ZoneEmploi;
								out1_tmp.Region = row1.Region;
								out1_tmp.Annee = row1.Annee;
								out1_tmp.Trimestre = row1.Trimestre;
								out1_tmp.DernierJourTrimestre = row1.DernierJourTrimestre;
								out1_tmp.EffectifSalarieBrut = row1.EffectifSalarieBrut;
								out1_tmp.EffectifSalarieCvs = row1.EffectifSalarieCvs;
								out1_tmp.MasseSalarialeBrut = row1.MasseSalarialeBrut;
								out1_tmp.MasseSalarialeCvs = row1.MasseSalarialeCvs;
								out1 = out1_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_1 = false;

							tos_count_tMap_1++;

							/**
							 * [tMap_1 main ] stop
							 */

							/**
							 * [tMap_1 process_data_begin ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_begin ] stop
							 */
// Start of branch "out1"
							if (out1 != null) {

								/**
								 * [tLogRow_1 main ] start
								 */

								currentComponent = "tLogRow_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "out1"

									);
								}

///////////////////////		

								String[] row_tLogRow_1 = new String[11];

								row_tLogRow_1[0] = String.valueOf(out1.Id);

								if (out1.LibelleZoneEmploi != null) { //
									row_tLogRow_1[1] = String.valueOf(out1.LibelleZoneEmploi);

								} //

								if (out1.ZoneEmploi != null) { //
									row_tLogRow_1[2] = String.valueOf(out1.ZoneEmploi);

								} //

								if (out1.Region != null) { //
									row_tLogRow_1[3] = String.valueOf(out1.Region);

								} //

								if (out1.Annee != null) { //
									row_tLogRow_1[4] = String.valueOf(out1.Annee);

								} //

								if (out1.Trimestre != null) { //
									row_tLogRow_1[5] = String.valueOf(out1.Trimestre);

								} //

								if (out1.DernierJourTrimestre != null) { //
									row_tLogRow_1[6] = FormatterUtils.format_Date(out1.DernierJourTrimestre,
											"yyyy-MM-dd");

								} //

								if (out1.EffectifSalarieBrut != null) { //
									row_tLogRow_1[7] = String.valueOf(out1.EffectifSalarieBrut);

								} //

								if (out1.EffectifSalarieCvs != null) { //
									row_tLogRow_1[8] = String.valueOf(out1.EffectifSalarieCvs);

								} //

								if (out1.MasseSalarialeBrut != null) { //
									row_tLogRow_1[9] = String.valueOf(out1.MasseSalarialeBrut);

								} //

								if (out1.MasseSalarialeCvs != null) { //
									row_tLogRow_1[10] = String.valueOf(out1.MasseSalarialeCvs);

								} //

								util_tLogRow_1.addRow(row_tLogRow_1);
								nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

								row2 = out1;

								tos_count_tLogRow_1++;

								/**
								 * [tLogRow_1 main ] stop
								 */

								/**
								 * [tLogRow_1 process_data_begin ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_begin ] stop
								 */

								/**
								 * [tReplicate_1 main ] start
								 */

								currentComponent = "tReplicate_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row2"

									);
								}

								row4 = new row4Struct();

								row4.Id = row2.Id;
								row4.LibelleZoneEmploi = row2.LibelleZoneEmploi;
								row4.ZoneEmploi = row2.ZoneEmploi;
								row4.Region = row2.Region;
								row4.Annee = row2.Annee;
								row4.Trimestre = row2.Trimestre;
								row4.DernierJourTrimestre = row2.DernierJourTrimestre;
								row4.EffectifSalarieBrut = row2.EffectifSalarieBrut;
								row4.EffectifSalarieCvs = row2.EffectifSalarieCvs;
								row4.MasseSalarialeBrut = row2.MasseSalarialeBrut;
								row4.MasseSalarialeCvs = row2.MasseSalarialeCvs;
								row3 = new row3Struct();

								row3.Id = row2.Id;
								row3.LibelleZoneEmploi = row2.LibelleZoneEmploi;
								row3.ZoneEmploi = row2.ZoneEmploi;
								row3.Region = row2.Region;
								row3.Annee = row2.Annee;
								row3.Trimestre = row2.Trimestre;
								row3.DernierJourTrimestre = row2.DernierJourTrimestre;
								row3.EffectifSalarieBrut = row2.EffectifSalarieBrut;
								row3.EffectifSalarieCvs = row2.EffectifSalarieCvs;
								row3.MasseSalarialeBrut = row2.MasseSalarialeBrut;
								row3.MasseSalarialeCvs = row2.MasseSalarialeCvs;

								tos_count_tReplicate_1++;

								/**
								 * [tReplicate_1 main ] stop
								 */

								/**
								 * [tReplicate_1 process_data_begin ] start
								 */

								currentComponent = "tReplicate_1";

								/**
								 * [tReplicate_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 main ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row4"

									);
								}

								StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
								sb_tFileOutputDelimited_1.append(row4.Id);
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.LibelleZoneEmploi != null) {
									sb_tFileOutputDelimited_1.append(row4.LibelleZoneEmploi);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.ZoneEmploi != null) {
									sb_tFileOutputDelimited_1.append(row4.ZoneEmploi);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.Region != null) {
									sb_tFileOutputDelimited_1.append(row4.Region);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.Annee != null) {
									sb_tFileOutputDelimited_1.append(row4.Annee);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.Trimestre != null) {
									sb_tFileOutputDelimited_1.append(row4.Trimestre);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.DernierJourTrimestre != null) {
									sb_tFileOutputDelimited_1.append(
											FormatterUtils.format_Date(row4.DernierJourTrimestre, "yyyy-MM-dd"));
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.EffectifSalarieBrut != null) {
									sb_tFileOutputDelimited_1.append(row4.EffectifSalarieBrut);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.EffectifSalarieCvs != null) {
									sb_tFileOutputDelimited_1.append(row4.EffectifSalarieCvs);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.MasseSalarialeBrut != null) {
									sb_tFileOutputDelimited_1.append(row4.MasseSalarialeBrut);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.MasseSalarialeCvs != null) {
									sb_tFileOutputDelimited_1.append(row4.MasseSalarialeCvs);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

								nb_line_tFileOutputDelimited_1++;
								resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

								outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());

								tos_count_tFileOutputDelimited_1++;

								/**
								 * [tFileOutputDelimited_1 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_end ] stop
								 */

								/**
								 * [tDBOutput_1 main ] start
								 */

								currentComponent = "tDBOutput_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row3"

									);
								}

								whetherReject_tDBOutput_1 = false;
								pstmt_tDBOutput_1.setInt(1, row3.Id);

								int checkCount_tDBOutput_1 = -1;
								try (java.sql.ResultSet rs_tDBOutput_1 = pstmt_tDBOutput_1.executeQuery()) {
									while (rs_tDBOutput_1.next()) {
										checkCount_tDBOutput_1 = rs_tDBOutput_1.getInt(1);
									}
								}
								if (checkCount_tDBOutput_1 > 0) {
									if (row3.LibelleZoneEmploi == null) {
										pstmtUpdate_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(1, row3.LibelleZoneEmploi);
									}

									if (row3.ZoneEmploi == null) {
										pstmtUpdate_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(2, row3.ZoneEmploi);
									}

									if (row3.Region == null) {
										pstmtUpdate_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
									} else {
										pstmtUpdate_tDBOutput_1.setString(3, row3.Region);
									}

									if (row3.Annee == null) {
										pstmtUpdate_tDBOutput_1.setNull(4, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(4, row3.Annee);
									}

									if (row3.Trimestre == null) {
										pstmtUpdate_tDBOutput_1.setNull(5, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(5, row3.Trimestre);
									}

									if (row3.DernierJourTrimestre != null) {
										date_tDBOutput_1 = row3.DernierJourTrimestre.getTime();
										if (date_tDBOutput_1 < year1_tDBOutput_1
												|| date_tDBOutput_1 >= year10000_tDBOutput_1) {
											pstmtUpdate_tDBOutput_1.setString(6, "0000-00-00 00:00:00");
										} else {
											pstmtUpdate_tDBOutput_1.setTimestamp(6,
													new java.sql.Timestamp(date_tDBOutput_1));
										}
									} else {
										pstmtUpdate_tDBOutput_1.setNull(6, java.sql.Types.DATE);
									}

									if (row3.EffectifSalarieBrut == null) {
										pstmtUpdate_tDBOutput_1.setNull(7, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(7, row3.EffectifSalarieBrut);
									}

									if (row3.EffectifSalarieCvs == null) {
										pstmtUpdate_tDBOutput_1.setNull(8, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setInt(8, row3.EffectifSalarieCvs);
									}

									if (row3.MasseSalarialeBrut == null) {
										pstmtUpdate_tDBOutput_1.setNull(9, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setLong(9, row3.MasseSalarialeBrut);
									}

									if (row3.MasseSalarialeCvs == null) {
										pstmtUpdate_tDBOutput_1.setNull(10, java.sql.Types.INTEGER);
									} else {
										pstmtUpdate_tDBOutput_1.setLong(10, row3.MasseSalarialeCvs);
									}

									pstmtUpdate_tDBOutput_1.setInt(11 + count_tDBOutput_1, row3.Id);

									try {
										int processedCount_tDBOutput_1 = pstmtUpdate_tDBOutput_1.executeUpdate();
										updatedCount_tDBOutput_1 += processedCount_tDBOutput_1;
										rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
										nb_line_tDBOutput_1++;
									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
										whetherReject_tDBOutput_1 = true;
										nb_line_tDBOutput_1++;
										System.err.print(e.getMessage());
									}
								} else {
									pstmtInsert_tDBOutput_1.setInt(1, row3.Id);

									if (row3.LibelleZoneEmploi == null) {
										pstmtInsert_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(2, row3.LibelleZoneEmploi);
									}

									if (row3.ZoneEmploi == null) {
										pstmtInsert_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(3, row3.ZoneEmploi);
									}

									if (row3.Region == null) {
										pstmtInsert_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
									} else {
										pstmtInsert_tDBOutput_1.setString(4, row3.Region);
									}

									if (row3.Annee == null) {
										pstmtInsert_tDBOutput_1.setNull(5, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(5, row3.Annee);
									}

									if (row3.Trimestre == null) {
										pstmtInsert_tDBOutput_1.setNull(6, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(6, row3.Trimestre);
									}

									if (row3.DernierJourTrimestre != null) {
										date_tDBOutput_1 = row3.DernierJourTrimestre.getTime();
										if (date_tDBOutput_1 < year1_tDBOutput_1
												|| date_tDBOutput_1 >= year10000_tDBOutput_1) {
											pstmtInsert_tDBOutput_1.setString(7, "0000-00-00 00:00:00");
										} else {
											pstmtInsert_tDBOutput_1.setTimestamp(7,
													new java.sql.Timestamp(date_tDBOutput_1));
										}
									} else {
										pstmtInsert_tDBOutput_1.setNull(7, java.sql.Types.DATE);
									}

									if (row3.EffectifSalarieBrut == null) {
										pstmtInsert_tDBOutput_1.setNull(8, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(8, row3.EffectifSalarieBrut);
									}

									if (row3.EffectifSalarieCvs == null) {
										pstmtInsert_tDBOutput_1.setNull(9, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setInt(9, row3.EffectifSalarieCvs);
									}

									if (row3.MasseSalarialeBrut == null) {
										pstmtInsert_tDBOutput_1.setNull(10, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setLong(10, row3.MasseSalarialeBrut);
									}

									if (row3.MasseSalarialeCvs == null) {
										pstmtInsert_tDBOutput_1.setNull(11, java.sql.Types.INTEGER);
									} else {
										pstmtInsert_tDBOutput_1.setLong(11, row3.MasseSalarialeCvs);
									}

									try {
										int processedCount_tDBOutput_1 = pstmtInsert_tDBOutput_1.executeUpdate();
										insertedCount_tDBOutput_1 += processedCount_tDBOutput_1;
										rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
										nb_line_tDBOutput_1++;
									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
										whetherReject_tDBOutput_1 = true;
										nb_line_tDBOutput_1++;
										System.err.print(e.getMessage());
									}
								}
								commitCounter_tDBOutput_1++;

								if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {

									if (rowsToCommitCount_tDBOutput_1 != 0) {
									}
									conn_tDBOutput_1.commit();
									if (rowsToCommitCount_tDBOutput_1 != 0) {
										rowsToCommitCount_tDBOutput_1 = 0;
									}
									commitCounter_tDBOutput_1 = 0;

								}

								tos_count_tDBOutput_1++;

								/**
								 * [tDBOutput_1 main ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_end ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_end ] stop
								 */

								/**
								 * [tReplicate_1 process_data_end ] start
								 */

								currentComponent = "tReplicate_1";

								/**
								 * [tReplicate_1 process_data_end ] stop
								 */

								/**
								 * [tLogRow_1 process_data_end ] start
								 */

								currentComponent = "tLogRow_1";

								/**
								 * [tLogRow_1 process_data_end ] stop
								 */

							} // End of branch "out1"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) ("C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/effectifs-salaries-et-masse-salariale-du-secteur-prive-par-zone-demploi.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out1");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tReplicate_1 end ] start
				 */

				currentComponent = "tReplicate_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tReplicate_1", true);
				end_Hash.put("tReplicate_1", System.currentTimeMillis());

				/**
				 * [tReplicate_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				currentComponent = "tDBOutput_1";

				if (pstmtUpdate_tDBOutput_1 != null) {
					pstmtUpdate_tDBOutput_1.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_1");
				}
				if (pstmtInsert_tDBOutput_1 != null) {
					pstmtInsert_tDBOutput_1.close();
					resourceMap.remove("pstmtInsert_tDBOutput_1");
				}
				if (pstmt_tDBOutput_1 != null) {
					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");
				}
				resourceMap.put("statementClosed_tDBOutput_1", true);
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

				}
				conn_tDBOutput_1.commit();
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

					rowsToCommitCount_tDBOutput_1 = 0;
				}
				commitCounter_tDBOutput_1 = 0;

				conn_tDBOutput_1.close();

				resourceMap.put("finish_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tReplicate_1 finally ] start
				 */

				currentComponent = "tReplicate_1";

				/**
				 * [tReplicate_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
						java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_1 = null;
						if ((pstmtUpdateToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtUpdate_tDBOutput_1")) != null) {
							pstmtUpdateToClose_tDBOutput_1.close();
						}
						java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_1 = null;
						if ((pstmtInsertToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtInsert_tDBOutput_1")) != null) {
							pstmtInsertToClose_tDBOutput_1.close();
						}
						java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
						if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_1")) != null) {
							pstmtToClose_tDBOutput_1.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_1") == null) {
						java.sql.Connection ctn_tDBOutput_1 = null;
						if ((ctn_tDBOutput_1 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_1")) != null) {
							try {
								ctn_tDBOutput_1.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_1) {
								String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :"
										+ sqlEx_tDBOutput_1.getMessage();
								System.err.println(errorMessage_tDBOutput_1);
							}
						}
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final EffectifSalarie EffectifSalarieClass = new EffectifSalarie();

		int exitCode = EffectifSalarieClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = EffectifSalarie.class.getClassLoader()
					.getResourceAsStream("datamspr/effectifsalarie_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = EffectifSalarie.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : EffectifSalarie");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 166185 characters generated by Talend Open Studio for Data Integration on the
 * 15 avril 2024, 17:48:10 CEST
 ************************************************************************************************/