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

package datamspr.deces_0_1;

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
 * Job: Deces Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Deces implements TalendJob {

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
	private final String jobName = "Deces";
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
					Deces.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Deces.this, new Object[] { e, currentComponent, globalMap });
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
		final static byte[] commonByteArrayLock_DATAMSPR_Deces = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Deces = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public Integer AnneeDeces;

		public Integer getAnneeDeces() {
			return this.AnneeDeces;
		}

		public Integer MoisDeces;

		public Integer getMoisDeces() {
			return this.MoisDeces;
		}

		public Integer JourDeces;

		public Integer getJourDeces() {
			return this.JourDeces;
		}

		public String DepartementDeces;

		public String getDepartementDeces() {
			return this.DepartementDeces;
		}

		public String LieuDeces;

		public String getLieuDeces() {
			return this.LieuDeces;
		}

		public String Sexe;

		public String getSexe() {
			return this.Sexe;
		}

		public Integer AnneeNaissance;

		public Integer getAnneeNaissance() {
			return this.AnneeNaissance;
		}

		public Integer MoisNaissance;

		public Integer getMoisNaissance() {
			return this.MoisNaissance;
		}

		public Integer JourNaissance;

		public Integer getJourNaissance() {
			return this.JourNaissance;
		}

		public String DepartementNaissance;

		public String getDepartementNaissance() {
			return this.DepartementNaissance;
		}

		public String Activite;

		public String getActivite() {
			return this.Activite;
		}

		public String IndicateurNationalite;

		public String getIndicateurNationalite() {
			return this.IndicateurNationalite;
		}

		public String DepartementDomicileDecede;

		public String getDepartementDomicileDecede() {
			return this.DepartementDomicileDecede;
		}

		public String CodeGeographique;

		public String getCodeGeographique() {
			return this.CodeGeographique;
		}

		public String EtatMatrimonial;

		public String getEtatMatrimonial() {
			return this.EtatMatrimonial;
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
			other.AnneeDeces = this.AnneeDeces;
			other.MoisDeces = this.MoisDeces;
			other.JourDeces = this.JourDeces;
			other.DepartementDeces = this.DepartementDeces;
			other.LieuDeces = this.LieuDeces;
			other.Sexe = this.Sexe;
			other.AnneeNaissance = this.AnneeNaissance;
			other.MoisNaissance = this.MoisNaissance;
			other.JourNaissance = this.JourNaissance;
			other.DepartementNaissance = this.DepartementNaissance;
			other.Activite = this.Activite;
			other.IndicateurNationalite = this.IndicateurNationalite;
			other.DepartementDomicileDecede = this.DepartementDomicileDecede;
			other.CodeGeographique = this.CodeGeographique;
			other.EtatMatrimonial = this.EtatMatrimonial;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.Id = this.Id;

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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Deces.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Deces.length == 0) {
						commonByteArray_DATAMSPR_Deces = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Deces = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Deces, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Deces, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_Deces.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Deces.length == 0) {
						commonByteArray_DATAMSPR_Deces = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Deces = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Deces, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Deces, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Deces) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeDeces = readInteger(dis);

					this.MoisDeces = readInteger(dis);

					this.JourDeces = readInteger(dis);

					this.DepartementDeces = readString(dis);

					this.LieuDeces = readString(dis);

					this.Sexe = readString(dis);

					this.AnneeNaissance = readInteger(dis);

					this.MoisNaissance = readInteger(dis);

					this.JourNaissance = readInteger(dis);

					this.DepartementNaissance = readString(dis);

					this.Activite = readString(dis);

					this.IndicateurNationalite = readString(dis);

					this.DepartementDomicileDecede = readString(dis);

					this.CodeGeographique = readString(dis);

					this.EtatMatrimonial = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Deces) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeDeces = readInteger(dis);

					this.MoisDeces = readInteger(dis);

					this.JourDeces = readInteger(dis);

					this.DepartementDeces = readString(dis);

					this.LieuDeces = readString(dis);

					this.Sexe = readString(dis);

					this.AnneeNaissance = readInteger(dis);

					this.MoisNaissance = readInteger(dis);

					this.JourNaissance = readInteger(dis);

					this.DepartementNaissance = readString(dis);

					this.Activite = readString(dis);

					this.IndicateurNationalite = readString(dis);

					this.DepartementDomicileDecede = readString(dis);

					this.CodeGeographique = readString(dis);

					this.EtatMatrimonial = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.AnneeDeces, dos);

				// Integer

				writeInteger(this.MoisDeces, dos);

				// Integer

				writeInteger(this.JourDeces, dos);

				// String

				writeString(this.DepartementDeces, dos);

				// String

				writeString(this.LieuDeces, dos);

				// String

				writeString(this.Sexe, dos);

				// Integer

				writeInteger(this.AnneeNaissance, dos);

				// Integer

				writeInteger(this.MoisNaissance, dos);

				// Integer

				writeInteger(this.JourNaissance, dos);

				// String

				writeString(this.DepartementNaissance, dos);

				// String

				writeString(this.Activite, dos);

				// String

				writeString(this.IndicateurNationalite, dos);

				// String

				writeString(this.DepartementDomicileDecede, dos);

				// String

				writeString(this.CodeGeographique, dos);

				// String

				writeString(this.EtatMatrimonial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.AnneeDeces, dos);

				// Integer

				writeInteger(this.MoisDeces, dos);

				// Integer

				writeInteger(this.JourDeces, dos);

				// String

				writeString(this.DepartementDeces, dos);

				// String

				writeString(this.LieuDeces, dos);

				// String

				writeString(this.Sexe, dos);

				// Integer

				writeInteger(this.AnneeNaissance, dos);

				// Integer

				writeInteger(this.MoisNaissance, dos);

				// Integer

				writeInteger(this.JourNaissance, dos);

				// String

				writeString(this.DepartementNaissance, dos);

				// String

				writeString(this.Activite, dos);

				// String

				writeString(this.IndicateurNationalite, dos);

				// String

				writeString(this.DepartementDomicileDecede, dos);

				// String

				writeString(this.CodeGeographique, dos);

				// String

				writeString(this.EtatMatrimonial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",AnneeDeces=" + String.valueOf(AnneeDeces));
			sb.append(",MoisDeces=" + String.valueOf(MoisDeces));
			sb.append(",JourDeces=" + String.valueOf(JourDeces));
			sb.append(",DepartementDeces=" + DepartementDeces);
			sb.append(",LieuDeces=" + LieuDeces);
			sb.append(",Sexe=" + Sexe);
			sb.append(",AnneeNaissance=" + String.valueOf(AnneeNaissance));
			sb.append(",MoisNaissance=" + String.valueOf(MoisNaissance));
			sb.append(",JourNaissance=" + String.valueOf(JourNaissance));
			sb.append(",DepartementNaissance=" + DepartementNaissance);
			sb.append(",Activite=" + Activite);
			sb.append(",IndicateurNationalite=" + IndicateurNationalite);
			sb.append(",DepartementDomicileDecede=" + DepartementDomicileDecede);
			sb.append(",CodeGeographique=" + CodeGeographique);
			sb.append(",EtatMatrimonial=" + EtatMatrimonial);
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
		final static byte[] commonByteArrayLock_DATAMSPR_Deces = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Deces = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public Integer AnneeDeces;

		public Integer getAnneeDeces() {
			return this.AnneeDeces;
		}

		public Integer MoisDeces;

		public Integer getMoisDeces() {
			return this.MoisDeces;
		}

		public Integer JourDeces;

		public Integer getJourDeces() {
			return this.JourDeces;
		}

		public String DepartementDeces;

		public String getDepartementDeces() {
			return this.DepartementDeces;
		}

		public String LieuDeces;

		public String getLieuDeces() {
			return this.LieuDeces;
		}

		public String Sexe;

		public String getSexe() {
			return this.Sexe;
		}

		public Integer AnneeNaissance;

		public Integer getAnneeNaissance() {
			return this.AnneeNaissance;
		}

		public Integer MoisNaissance;

		public Integer getMoisNaissance() {
			return this.MoisNaissance;
		}

		public Integer JourNaissance;

		public Integer getJourNaissance() {
			return this.JourNaissance;
		}

		public String DepartementNaissance;

		public String getDepartementNaissance() {
			return this.DepartementNaissance;
		}

		public String Activite;

		public String getActivite() {
			return this.Activite;
		}

		public String IndicateurNationalite;

		public String getIndicateurNationalite() {
			return this.IndicateurNationalite;
		}

		public String DepartementDomicileDecede;

		public String getDepartementDomicileDecede() {
			return this.DepartementDomicileDecede;
		}

		public String CodeGeographique;

		public String getCodeGeographique() {
			return this.CodeGeographique;
		}

		public String EtatMatrimonial;

		public String getEtatMatrimonial() {
			return this.EtatMatrimonial;
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
			other.AnneeDeces = this.AnneeDeces;
			other.MoisDeces = this.MoisDeces;
			other.JourDeces = this.JourDeces;
			other.DepartementDeces = this.DepartementDeces;
			other.LieuDeces = this.LieuDeces;
			other.Sexe = this.Sexe;
			other.AnneeNaissance = this.AnneeNaissance;
			other.MoisNaissance = this.MoisNaissance;
			other.JourNaissance = this.JourNaissance;
			other.DepartementNaissance = this.DepartementNaissance;
			other.Activite = this.Activite;
			other.IndicateurNationalite = this.IndicateurNationalite;
			other.DepartementDomicileDecede = this.DepartementDomicileDecede;
			other.CodeGeographique = this.CodeGeographique;
			other.EtatMatrimonial = this.EtatMatrimonial;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.Id = this.Id;

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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Deces.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Deces.length == 0) {
						commonByteArray_DATAMSPR_Deces = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Deces = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Deces, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Deces, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_Deces.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Deces.length == 0) {
						commonByteArray_DATAMSPR_Deces = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Deces = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Deces, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Deces, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Deces) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeDeces = readInteger(dis);

					this.MoisDeces = readInteger(dis);

					this.JourDeces = readInteger(dis);

					this.DepartementDeces = readString(dis);

					this.LieuDeces = readString(dis);

					this.Sexe = readString(dis);

					this.AnneeNaissance = readInteger(dis);

					this.MoisNaissance = readInteger(dis);

					this.JourNaissance = readInteger(dis);

					this.DepartementNaissance = readString(dis);

					this.Activite = readString(dis);

					this.IndicateurNationalite = readString(dis);

					this.DepartementDomicileDecede = readString(dis);

					this.CodeGeographique = readString(dis);

					this.EtatMatrimonial = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Deces) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeDeces = readInteger(dis);

					this.MoisDeces = readInteger(dis);

					this.JourDeces = readInteger(dis);

					this.DepartementDeces = readString(dis);

					this.LieuDeces = readString(dis);

					this.Sexe = readString(dis);

					this.AnneeNaissance = readInteger(dis);

					this.MoisNaissance = readInteger(dis);

					this.JourNaissance = readInteger(dis);

					this.DepartementNaissance = readString(dis);

					this.Activite = readString(dis);

					this.IndicateurNationalite = readString(dis);

					this.DepartementDomicileDecede = readString(dis);

					this.CodeGeographique = readString(dis);

					this.EtatMatrimonial = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.AnneeDeces, dos);

				// Integer

				writeInteger(this.MoisDeces, dos);

				// Integer

				writeInteger(this.JourDeces, dos);

				// String

				writeString(this.DepartementDeces, dos);

				// String

				writeString(this.LieuDeces, dos);

				// String

				writeString(this.Sexe, dos);

				// Integer

				writeInteger(this.AnneeNaissance, dos);

				// Integer

				writeInteger(this.MoisNaissance, dos);

				// Integer

				writeInteger(this.JourNaissance, dos);

				// String

				writeString(this.DepartementNaissance, dos);

				// String

				writeString(this.Activite, dos);

				// String

				writeString(this.IndicateurNationalite, dos);

				// String

				writeString(this.DepartementDomicileDecede, dos);

				// String

				writeString(this.CodeGeographique, dos);

				// String

				writeString(this.EtatMatrimonial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.AnneeDeces, dos);

				// Integer

				writeInteger(this.MoisDeces, dos);

				// Integer

				writeInteger(this.JourDeces, dos);

				// String

				writeString(this.DepartementDeces, dos);

				// String

				writeString(this.LieuDeces, dos);

				// String

				writeString(this.Sexe, dos);

				// Integer

				writeInteger(this.AnneeNaissance, dos);

				// Integer

				writeInteger(this.MoisNaissance, dos);

				// Integer

				writeInteger(this.JourNaissance, dos);

				// String

				writeString(this.DepartementNaissance, dos);

				// String

				writeString(this.Activite, dos);

				// String

				writeString(this.IndicateurNationalite, dos);

				// String

				writeString(this.DepartementDomicileDecede, dos);

				// String

				writeString(this.CodeGeographique, dos);

				// String

				writeString(this.EtatMatrimonial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",AnneeDeces=" + String.valueOf(AnneeDeces));
			sb.append(",MoisDeces=" + String.valueOf(MoisDeces));
			sb.append(",JourDeces=" + String.valueOf(JourDeces));
			sb.append(",DepartementDeces=" + DepartementDeces);
			sb.append(",LieuDeces=" + LieuDeces);
			sb.append(",Sexe=" + Sexe);
			sb.append(",AnneeNaissance=" + String.valueOf(AnneeNaissance));
			sb.append(",MoisNaissance=" + String.valueOf(MoisNaissance));
			sb.append(",JourNaissance=" + String.valueOf(JourNaissance));
			sb.append(",DepartementNaissance=" + DepartementNaissance);
			sb.append(",Activite=" + Activite);
			sb.append(",IndicateurNationalite=" + IndicateurNationalite);
			sb.append(",DepartementDomicileDecede=" + DepartementDomicileDecede);
			sb.append(",CodeGeographique=" + CodeGeographique);
			sb.append(",EtatMatrimonial=" + EtatMatrimonial);
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

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_Deces = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Deces = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public Integer AnneeDeces;

		public Integer getAnneeDeces() {
			return this.AnneeDeces;
		}

		public Integer MoisDeces;

		public Integer getMoisDeces() {
			return this.MoisDeces;
		}

		public Integer JourDeces;

		public Integer getJourDeces() {
			return this.JourDeces;
		}

		public String DepartementDeces;

		public String getDepartementDeces() {
			return this.DepartementDeces;
		}

		public String LieuDeces;

		public String getLieuDeces() {
			return this.LieuDeces;
		}

		public String Sexe;

		public String getSexe() {
			return this.Sexe;
		}

		public Integer AnneeNaissance;

		public Integer getAnneeNaissance() {
			return this.AnneeNaissance;
		}

		public Integer MoisNaissance;

		public Integer getMoisNaissance() {
			return this.MoisNaissance;
		}

		public Integer JourNaissance;

		public Integer getJourNaissance() {
			return this.JourNaissance;
		}

		public String DepartementNaissance;

		public String getDepartementNaissance() {
			return this.DepartementNaissance;
		}

		public String Activite;

		public String getActivite() {
			return this.Activite;
		}

		public String IndicateurNationalite;

		public String getIndicateurNationalite() {
			return this.IndicateurNationalite;
		}

		public String DepartementDomicileDecede;

		public String getDepartementDomicileDecede() {
			return this.DepartementDomicileDecede;
		}

		public String CodeGeographique;

		public String getCodeGeographique() {
			return this.CodeGeographique;
		}

		public String EtatMatrimonial;

		public String getEtatMatrimonial() {
			return this.EtatMatrimonial;
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
			other.AnneeDeces = this.AnneeDeces;
			other.MoisDeces = this.MoisDeces;
			other.JourDeces = this.JourDeces;
			other.DepartementDeces = this.DepartementDeces;
			other.LieuDeces = this.LieuDeces;
			other.Sexe = this.Sexe;
			other.AnneeNaissance = this.AnneeNaissance;
			other.MoisNaissance = this.MoisNaissance;
			other.JourNaissance = this.JourNaissance;
			other.DepartementNaissance = this.DepartementNaissance;
			other.Activite = this.Activite;
			other.IndicateurNationalite = this.IndicateurNationalite;
			other.DepartementDomicileDecede = this.DepartementDomicileDecede;
			other.CodeGeographique = this.CodeGeographique;
			other.EtatMatrimonial = this.EtatMatrimonial;

		}

		public void copyKeysDataTo(out1Struct other) {

			other.Id = this.Id;

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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Deces.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Deces.length == 0) {
						commonByteArray_DATAMSPR_Deces = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Deces = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Deces, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Deces, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_Deces.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Deces.length == 0) {
						commonByteArray_DATAMSPR_Deces = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Deces = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Deces, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Deces, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Deces) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeDeces = readInteger(dis);

					this.MoisDeces = readInteger(dis);

					this.JourDeces = readInteger(dis);

					this.DepartementDeces = readString(dis);

					this.LieuDeces = readString(dis);

					this.Sexe = readString(dis);

					this.AnneeNaissance = readInteger(dis);

					this.MoisNaissance = readInteger(dis);

					this.JourNaissance = readInteger(dis);

					this.DepartementNaissance = readString(dis);

					this.Activite = readString(dis);

					this.IndicateurNationalite = readString(dis);

					this.DepartementDomicileDecede = readString(dis);

					this.CodeGeographique = readString(dis);

					this.EtatMatrimonial = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Deces) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.AnneeDeces = readInteger(dis);

					this.MoisDeces = readInteger(dis);

					this.JourDeces = readInteger(dis);

					this.DepartementDeces = readString(dis);

					this.LieuDeces = readString(dis);

					this.Sexe = readString(dis);

					this.AnneeNaissance = readInteger(dis);

					this.MoisNaissance = readInteger(dis);

					this.JourNaissance = readInteger(dis);

					this.DepartementNaissance = readString(dis);

					this.Activite = readString(dis);

					this.IndicateurNationalite = readString(dis);

					this.DepartementDomicileDecede = readString(dis);

					this.CodeGeographique = readString(dis);

					this.EtatMatrimonial = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.AnneeDeces, dos);

				// Integer

				writeInteger(this.MoisDeces, dos);

				// Integer

				writeInteger(this.JourDeces, dos);

				// String

				writeString(this.DepartementDeces, dos);

				// String

				writeString(this.LieuDeces, dos);

				// String

				writeString(this.Sexe, dos);

				// Integer

				writeInteger(this.AnneeNaissance, dos);

				// Integer

				writeInteger(this.MoisNaissance, dos);

				// Integer

				writeInteger(this.JourNaissance, dos);

				// String

				writeString(this.DepartementNaissance, dos);

				// String

				writeString(this.Activite, dos);

				// String

				writeString(this.IndicateurNationalite, dos);

				// String

				writeString(this.DepartementDomicileDecede, dos);

				// String

				writeString(this.CodeGeographique, dos);

				// String

				writeString(this.EtatMatrimonial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// Integer

				writeInteger(this.AnneeDeces, dos);

				// Integer

				writeInteger(this.MoisDeces, dos);

				// Integer

				writeInteger(this.JourDeces, dos);

				// String

				writeString(this.DepartementDeces, dos);

				// String

				writeString(this.LieuDeces, dos);

				// String

				writeString(this.Sexe, dos);

				// Integer

				writeInteger(this.AnneeNaissance, dos);

				// Integer

				writeInteger(this.MoisNaissance, dos);

				// Integer

				writeInteger(this.JourNaissance, dos);

				// String

				writeString(this.DepartementNaissance, dos);

				// String

				writeString(this.Activite, dos);

				// String

				writeString(this.IndicateurNationalite, dos);

				// String

				writeString(this.DepartementDomicileDecede, dos);

				// String

				writeString(this.CodeGeographique, dos);

				// String

				writeString(this.EtatMatrimonial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",AnneeDeces=" + String.valueOf(AnneeDeces));
			sb.append(",MoisDeces=" + String.valueOf(MoisDeces));
			sb.append(",JourDeces=" + String.valueOf(JourDeces));
			sb.append(",DepartementDeces=" + DepartementDeces);
			sb.append(",LieuDeces=" + LieuDeces);
			sb.append(",Sexe=" + Sexe);
			sb.append(",AnneeNaissance=" + String.valueOf(AnneeNaissance));
			sb.append(",MoisNaissance=" + String.valueOf(MoisNaissance));
			sb.append(",JourNaissance=" + String.valueOf(JourNaissance));
			sb.append(",DepartementNaissance=" + DepartementNaissance);
			sb.append(",Activite=" + Activite);
			sb.append(",IndicateurNationalite=" + IndicateurNationalite);
			sb.append(",DepartementDomicileDecede=" + DepartementDomicileDecede);
			sb.append(",CodeGeographique=" + CodeGeographique);
			sb.append(",EtatMatrimonial=" + EtatMatrimonial);
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
		final static byte[] commonByteArrayLock_DATAMSPR_Deces = new byte[0];
		static byte[] commonByteArray_DATAMSPR_Deces = new byte[0];

		public Integer AnneeDeces;

		public Integer getAnneeDeces() {
			return this.AnneeDeces;
		}

		public Integer MoisDeces;

		public Integer getMoisDeces() {
			return this.MoisDeces;
		}

		public Integer JourDeces;

		public Integer getJourDeces() {
			return this.JourDeces;
		}

		public String DepartementDeces;

		public String getDepartementDeces() {
			return this.DepartementDeces;
		}

		public String LieuDeces;

		public String getLieuDeces() {
			return this.LieuDeces;
		}

		public String Sexe;

		public String getSexe() {
			return this.Sexe;
		}

		public Integer AnneeNaissance;

		public Integer getAnneeNaissance() {
			return this.AnneeNaissance;
		}

		public Integer MoisNaissance;

		public Integer getMoisNaissance() {
			return this.MoisNaissance;
		}

		public Integer JourNaissance;

		public Integer getJourNaissance() {
			return this.JourNaissance;
		}

		public String DepartementNaissance;

		public String getDepartementNaissance() {
			return this.DepartementNaissance;
		}

		public String Activite;

		public String getActivite() {
			return this.Activite;
		}

		public String IndicateurNationalite;

		public String getIndicateurNationalite() {
			return this.IndicateurNationalite;
		}

		public String DepartementDomicileDecede;

		public String getDepartementDomicileDecede() {
			return this.DepartementDomicileDecede;
		}

		public String CodeGeographique;

		public String getCodeGeographique() {
			return this.CodeGeographique;
		}

		public String TrancheUniteUrbaineDomicile;

		public String getTrancheUniteUrbaineDomicile() {
			return this.TrancheUniteUrbaineDomicile;
		}

		public String TrancheCommuneDomicile;

		public String getTrancheCommuneDomicile() {
			return this.TrancheCommuneDomicile;
		}

		public String EtatMatrimonial;

		public String getEtatMatrimonial() {
			return this.EtatMatrimonial;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_Deces.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Deces.length == 0) {
						commonByteArray_DATAMSPR_Deces = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Deces = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_Deces, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Deces, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_Deces.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_Deces.length == 0) {
						commonByteArray_DATAMSPR_Deces = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_Deces = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_Deces, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_Deces, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Deces) {

				try {

					int length = 0;

					this.AnneeDeces = readInteger(dis);

					this.MoisDeces = readInteger(dis);

					this.JourDeces = readInteger(dis);

					this.DepartementDeces = readString(dis);

					this.LieuDeces = readString(dis);

					this.Sexe = readString(dis);

					this.AnneeNaissance = readInteger(dis);

					this.MoisNaissance = readInteger(dis);

					this.JourNaissance = readInteger(dis);

					this.DepartementNaissance = readString(dis);

					this.Activite = readString(dis);

					this.IndicateurNationalite = readString(dis);

					this.DepartementDomicileDecede = readString(dis);

					this.CodeGeographique = readString(dis);

					this.TrancheUniteUrbaineDomicile = readString(dis);

					this.TrancheCommuneDomicile = readString(dis);

					this.EtatMatrimonial = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_Deces) {

				try {

					int length = 0;

					this.AnneeDeces = readInteger(dis);

					this.MoisDeces = readInteger(dis);

					this.JourDeces = readInteger(dis);

					this.DepartementDeces = readString(dis);

					this.LieuDeces = readString(dis);

					this.Sexe = readString(dis);

					this.AnneeNaissance = readInteger(dis);

					this.MoisNaissance = readInteger(dis);

					this.JourNaissance = readInteger(dis);

					this.DepartementNaissance = readString(dis);

					this.Activite = readString(dis);

					this.IndicateurNationalite = readString(dis);

					this.DepartementDomicileDecede = readString(dis);

					this.CodeGeographique = readString(dis);

					this.TrancheUniteUrbaineDomicile = readString(dis);

					this.TrancheCommuneDomicile = readString(dis);

					this.EtatMatrimonial = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.AnneeDeces, dos);

				// Integer

				writeInteger(this.MoisDeces, dos);

				// Integer

				writeInteger(this.JourDeces, dos);

				// String

				writeString(this.DepartementDeces, dos);

				// String

				writeString(this.LieuDeces, dos);

				// String

				writeString(this.Sexe, dos);

				// Integer

				writeInteger(this.AnneeNaissance, dos);

				// Integer

				writeInteger(this.MoisNaissance, dos);

				// Integer

				writeInteger(this.JourNaissance, dos);

				// String

				writeString(this.DepartementNaissance, dos);

				// String

				writeString(this.Activite, dos);

				// String

				writeString(this.IndicateurNationalite, dos);

				// String

				writeString(this.DepartementDomicileDecede, dos);

				// String

				writeString(this.CodeGeographique, dos);

				// String

				writeString(this.TrancheUniteUrbaineDomicile, dos);

				// String

				writeString(this.TrancheCommuneDomicile, dos);

				// String

				writeString(this.EtatMatrimonial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.AnneeDeces, dos);

				// Integer

				writeInteger(this.MoisDeces, dos);

				// Integer

				writeInteger(this.JourDeces, dos);

				// String

				writeString(this.DepartementDeces, dos);

				// String

				writeString(this.LieuDeces, dos);

				// String

				writeString(this.Sexe, dos);

				// Integer

				writeInteger(this.AnneeNaissance, dos);

				// Integer

				writeInteger(this.MoisNaissance, dos);

				// Integer

				writeInteger(this.JourNaissance, dos);

				// String

				writeString(this.DepartementNaissance, dos);

				// String

				writeString(this.Activite, dos);

				// String

				writeString(this.IndicateurNationalite, dos);

				// String

				writeString(this.DepartementDomicileDecede, dos);

				// String

				writeString(this.CodeGeographique, dos);

				// String

				writeString(this.TrancheUniteUrbaineDomicile, dos);

				// String

				writeString(this.TrancheCommuneDomicile, dos);

				// String

				writeString(this.EtatMatrimonial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("AnneeDeces=" + String.valueOf(AnneeDeces));
			sb.append(",MoisDeces=" + String.valueOf(MoisDeces));
			sb.append(",JourDeces=" + String.valueOf(JourDeces));
			sb.append(",DepartementDeces=" + DepartementDeces);
			sb.append(",LieuDeces=" + LieuDeces);
			sb.append(",Sexe=" + Sexe);
			sb.append(",AnneeNaissance=" + String.valueOf(AnneeNaissance));
			sb.append(",MoisNaissance=" + String.valueOf(MoisNaissance));
			sb.append(",JourNaissance=" + String.valueOf(JourNaissance));
			sb.append(",DepartementNaissance=" + DepartementNaissance);
			sb.append(",Activite=" + Activite);
			sb.append(",IndicateurNationalite=" + IndicateurNationalite);
			sb.append(",DepartementDomicileDecede=" + DepartementDomicileDecede);
			sb.append(",CodeGeographique=" + CodeGeographique);
			sb.append(",TrancheUniteUrbaineDomicile=" + TrancheUniteUrbaineDomicile);
			sb.append(",TrancheCommuneDomicile=" + TrancheCommuneDomicile);
			sb.append(",EtatMatrimonial=" + EtatMatrimonial);
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
						"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_output/Deces.csv"))
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
					outtFileOutputDelimited_1.write("AnneeDeces");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("MoisDeces");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("JourDeces");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("DepartementDeces");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("LieuDeces");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Sexe");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("AnneeNaissance");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("MoisNaissance");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("JourNaissance");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("DepartementNaissance");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("Activite");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("IndicateurNationalite");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("DepartementDomicileDecede");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("CodeGeographique");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("EtatMatrimonial");
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

				String tableName_tDBOutput_1 = "Deces";
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
						.decryptPassword("enc:routine.encryption.key.v1:zRFg+tx5Ovc/6uRsWuzmKrPC3BSTk/W0QO/Fd7E+vmw=");

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
					if (table_tDBOutput_1.equalsIgnoreCase("Deces")) {
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
							+ "`(`Id` INT(255)   not null ,`AnneeDeces` INT(255)  ,`MoisDeces` INT(255)  ,`JourDeces` INT(255)  ,`DepartementDeces` VARCHAR(255)  ,`LieuDeces` VARCHAR(255)  ,`Sexe` VARCHAR(255)  ,`AnneeNaissance` INT(255)  ,`MoisNaissance` INT(255)  ,`JourNaissance` INT(255)  ,`DepartementNaissance` VARCHAR(255)  ,`Activite` VARCHAR(255)  ,`IndicateurNationalite` VARCHAR(255)  ,`DepartementDomicileDecede` VARCHAR(255)  ,`CodeGeographique` VARCHAR(255)  ,`EtatMatrimonial` VARCHAR(255)  ,primary key(`Id`))");
				}

				String insert_tDBOutput_1 = "INSERT INTO `" + "Deces"
						+ "` (`Id`,`AnneeDeces`,`MoisDeces`,`JourDeces`,`DepartementDeces`,`LieuDeces`,`Sexe`,`AnneeNaissance`,`MoisNaissance`,`JourNaissance`,`DepartementNaissance`,`Activite`,`IndicateurNationalite`,`DepartementDomicileDecede`,`CodeGeographique`,`EtatMatrimonial`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				int batchSize_tDBOutput_1 = 10000;
				int batchSizeCounter_tDBOutput_1 = 0;

				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

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
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "out1");
				}

				int tos_count_tReplicate_1 = 0;

				/**
				 * [tReplicate_1 begin ] stop
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
					String LieuDeces;
					String Sexe;
					String Activite;
					String IndicateurNationalite;
					String EtatMatrimonial;
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

					Object filename_tFileInputDelimited_1 = "C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/FD_DEC_2019.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/FD_DEC_2019.csv",
								"UTF-8", ";", "\n", false, 1, 0, limit_tFileInputDelimited_1, -1, false);
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

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AnneeDeces = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AnneeDeces", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AnneeDeces = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 1;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.MoisDeces = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"MoisDeces", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.MoisDeces = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 2;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.JourDeces = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"JourDeces", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.JourDeces = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row1.DepartementDeces = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row1.LieuDeces = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							row1.Sexe = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 6;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.AnneeNaissance = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"AnneeNaissance", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.AnneeNaissance = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 7;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.MoisNaissance = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"MoisNaissance", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.MoisNaissance = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 8;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.JourNaissance = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"JourNaissance", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.JourNaissance = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 9;

							row1.DepartementNaissance = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 10;

							row1.Activite = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 11;

							row1.IndicateurNationalite = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 12;

							row1.DepartementDomicileDecede = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 13;

							row1.CodeGeographique = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 14;

							row1.TrancheUniteUrbaineDomicile = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 15;

							row1.TrancheCommuneDomicile = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 16;

							row1.EtatMatrimonial = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

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
								Var.Id = Numeric.sequence("Id", 1, 1);
								Var.LieuDeces = row1.LieuDeces.equals("LOG") ? "logement ou domicile"
										: row1.LieuDeces.equals("EHC") ? "établissement hospitalier ou clinique privée"
												: row1.LieuDeces.equals("HMR") ? "hospice, maison de retraite"
														: row1.LieuDeces.equals("AUT") ? "autre" : "valeur inconnue";
								Var.Sexe = row1.Sexe.equals("1") ? "masculin"
										: row1.Sexe.equals("2") ? "féminin" : "valeur inconnue";
								Var.Activite = row1.Activite.equals("1") ? "retraité"
										: row1.Activite.equals("2") ? "inactif"
												: row1.Activite.equals("3") ? "actif (y compris demandeur d’emploi)"
														: "valeur inconnue";
								Var.IndicateurNationalite = row1.IndicateurNationalite.equals("1") ? "française"
										: row1.IndicateurNationalite.equals("2") ? "étrangère" : "valeur inconnue";
								Var.EtatMatrimonial = row1.EtatMatrimonial.equals("1") ? "célibataire"
										: row1.EtatMatrimonial.equals("2") ? "marié"
												: row1.EtatMatrimonial.equals("3") ? "veuf"
														: row1.EtatMatrimonial.equals("4") ? "divorcé"
																: "valeur inconnue";// ###############################
																					// ###############################
																					// # Output tables

								out1 = null;

// # Output table : 'out1'
								out1_tmp.Id = Var.Id;
								out1_tmp.AnneeDeces = row1.AnneeDeces;
								out1_tmp.MoisDeces = row1.MoisDeces;
								out1_tmp.JourDeces = row1.JourDeces;
								out1_tmp.DepartementDeces = row1.DepartementDeces;
								out1_tmp.LieuDeces = Var.LieuDeces;
								out1_tmp.Sexe = Var.Sexe;
								out1_tmp.AnneeNaissance = row1.AnneeNaissance;
								out1_tmp.MoisNaissance = row1.MoisNaissance;
								out1_tmp.JourNaissance = row1.JourNaissance;
								out1_tmp.DepartementNaissance = row1.DepartementNaissance;
								out1_tmp.Activite = Var.Activite;
								out1_tmp.IndicateurNationalite = Var.IndicateurNationalite;
								out1_tmp.DepartementDomicileDecede = row1.DepartementDomicileDecede;
								out1_tmp.CodeGeographique = row1.CodeGeographique;
								out1_tmp.EtatMatrimonial = Var.EtatMatrimonial;
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
								 * [tReplicate_1 main ] start
								 */

								currentComponent = "tReplicate_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "out1"

									);
								}

								row4 = new row4Struct();

								row4.Id = out1.Id;
								row4.AnneeDeces = out1.AnneeDeces;
								row4.MoisDeces = out1.MoisDeces;
								row4.JourDeces = out1.JourDeces;
								row4.DepartementDeces = out1.DepartementDeces;
								row4.LieuDeces = out1.LieuDeces;
								row4.Sexe = out1.Sexe;
								row4.AnneeNaissance = out1.AnneeNaissance;
								row4.MoisNaissance = out1.MoisNaissance;
								row4.JourNaissance = out1.JourNaissance;
								row4.DepartementNaissance = out1.DepartementNaissance;
								row4.Activite = out1.Activite;
								row4.IndicateurNationalite = out1.IndicateurNationalite;
								row4.DepartementDomicileDecede = out1.DepartementDomicileDecede;
								row4.CodeGeographique = out1.CodeGeographique;
								row4.EtatMatrimonial = out1.EtatMatrimonial;
								row3 = new row3Struct();

								row3.Id = out1.Id;
								row3.AnneeDeces = out1.AnneeDeces;
								row3.MoisDeces = out1.MoisDeces;
								row3.JourDeces = out1.JourDeces;
								row3.DepartementDeces = out1.DepartementDeces;
								row3.LieuDeces = out1.LieuDeces;
								row3.Sexe = out1.Sexe;
								row3.AnneeNaissance = out1.AnneeNaissance;
								row3.MoisNaissance = out1.MoisNaissance;
								row3.JourNaissance = out1.JourNaissance;
								row3.DepartementNaissance = out1.DepartementNaissance;
								row3.Activite = out1.Activite;
								row3.IndicateurNationalite = out1.IndicateurNationalite;
								row3.DepartementDomicileDecede = out1.DepartementDomicileDecede;
								row3.CodeGeographique = out1.CodeGeographique;
								row3.EtatMatrimonial = out1.EtatMatrimonial;

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
								if (row4.AnneeDeces != null) {
									sb_tFileOutputDelimited_1.append(row4.AnneeDeces);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.MoisDeces != null) {
									sb_tFileOutputDelimited_1.append(row4.MoisDeces);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.JourDeces != null) {
									sb_tFileOutputDelimited_1.append(row4.JourDeces);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.DepartementDeces != null) {
									sb_tFileOutputDelimited_1.append(row4.DepartementDeces);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.LieuDeces != null) {
									sb_tFileOutputDelimited_1.append(row4.LieuDeces);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.Sexe != null) {
									sb_tFileOutputDelimited_1.append(row4.Sexe);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.AnneeNaissance != null) {
									sb_tFileOutputDelimited_1.append(row4.AnneeNaissance);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.MoisNaissance != null) {
									sb_tFileOutputDelimited_1.append(row4.MoisNaissance);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.JourNaissance != null) {
									sb_tFileOutputDelimited_1.append(row4.JourNaissance);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.DepartementNaissance != null) {
									sb_tFileOutputDelimited_1.append(row4.DepartementNaissance);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.Activite != null) {
									sb_tFileOutputDelimited_1.append(row4.Activite);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.IndicateurNationalite != null) {
									sb_tFileOutputDelimited_1.append(row4.IndicateurNationalite);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.DepartementDomicileDecede != null) {
									sb_tFileOutputDelimited_1.append(row4.DepartementDomicileDecede);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.CodeGeographique != null) {
									sb_tFileOutputDelimited_1.append(row4.CodeGeographique);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (row4.EtatMatrimonial != null) {
									sb_tFileOutputDelimited_1.append(row4.EtatMatrimonial);
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

								if (row3.AnneeDeces == null) {
									pstmt_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(2, row3.AnneeDeces);
								}

								if (row3.MoisDeces == null) {
									pstmt_tDBOutput_1.setNull(3, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(3, row3.MoisDeces);
								}

								if (row3.JourDeces == null) {
									pstmt_tDBOutput_1.setNull(4, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(4, row3.JourDeces);
								}

								if (row3.DepartementDeces == null) {
									pstmt_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(5, row3.DepartementDeces);
								}

								if (row3.LieuDeces == null) {
									pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(6, row3.LieuDeces);
								}

								if (row3.Sexe == null) {
									pstmt_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(7, row3.Sexe);
								}

								if (row3.AnneeNaissance == null) {
									pstmt_tDBOutput_1.setNull(8, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(8, row3.AnneeNaissance);
								}

								if (row3.MoisNaissance == null) {
									pstmt_tDBOutput_1.setNull(9, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(9, row3.MoisNaissance);
								}

								if (row3.JourNaissance == null) {
									pstmt_tDBOutput_1.setNull(10, java.sql.Types.INTEGER);
								} else {
									pstmt_tDBOutput_1.setInt(10, row3.JourNaissance);
								}

								if (row3.DepartementNaissance == null) {
									pstmt_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(11, row3.DepartementNaissance);
								}

								if (row3.Activite == null) {
									pstmt_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(12, row3.Activite);
								}

								if (row3.IndicateurNationalite == null) {
									pstmt_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(13, row3.IndicateurNationalite);
								}

								if (row3.DepartementDomicileDecede == null) {
									pstmt_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(14, row3.DepartementDomicileDecede);
								}

								if (row3.CodeGeographique == null) {
									pstmt_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(15, row3.CodeGeographique);
								}

								if (row3.EtatMatrimonial == null) {
									pstmt_tDBOutput_1.setNull(16, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(16, row3.EtatMatrimonial);
								}

								pstmt_tDBOutput_1.addBatch();
								nb_line_tDBOutput_1++;

								batchSizeCounter_tDBOutput_1++;
								if (batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1) {
									try {
										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED
													? 0
													: 1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
									} catch (java.sql.BatchUpdateException e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0
													: countEach_tDBOutput_1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
										System.err.println(e.getMessage());
									}

									batchSizeCounter_tDBOutput_1 = 0;
								}
								commitCounter_tDBOutput_1++;

								if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {

									try {
										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : 1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
									} catch (java.sql.BatchUpdateException e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0
													: countEach_tDBOutput_1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
										System.err.println(e.getMessage());

									}
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
					if (!((Object) ("C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/FD_DEC_2019.csv") instanceof java.io.InputStream)) {
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
				 * [tReplicate_1 end ] start
				 */

				currentComponent = "tReplicate_1";

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "out1");
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

				try {
					if (batchSizeCounter_tDBOutput_1 != 0) {
						int countSum_tDBOutput_1 = 0;

						for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
							countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED ? 0
									: 1);
						}
						rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

						insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					}

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_1 = 0;
					for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
						countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					System.err.println(e.getMessage());

				}
				batchSizeCounter_tDBOutput_1 = 0;

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
		final Deces DecesClass = new Deces();

		int exitCode = DecesClass.runJobInTOS(args);

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
			java.io.InputStream inContext = Deces.class.getClassLoader()
					.getResourceAsStream("datamspr/deces_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Deces.class.getClassLoader()
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
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Deces");
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
 * 133236 characters generated by Talend Open Studio for Data Integration on the
 * 15 avril 2024, 17:47:28 CEST
 ************************************************************************************************/