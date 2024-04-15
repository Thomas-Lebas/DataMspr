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

package datamspr.voteelection2017_0_1;

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
 * Job: VoteElection2017 Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class VoteElection2017 implements TalendJob {

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
	private final String jobName = "VoteElection2017";
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
					VoteElection2017.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(VoteElection2017.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileInputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tReplicate_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_VoteElection2017 = new byte[0];
		static byte[] commonByteArray_DATAMSPR_VoteElection2017 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public String CodeDepartement;

		public String getCodeDepartement() {
			return this.CodeDepartement;
		}

		public String LibelleDepartement;

		public String getLibelleDepartement() {
			return this.LibelleDepartement;
		}

		public String CodeCirconscription;

		public String getCodeCirconscription() {
			return this.CodeCirconscription;
		}

		public String LibelleCirconscription;

		public String getLibelleCirconscription() {
			return this.LibelleCirconscription;
		}

		public Integer Inscrit;

		public Integer getInscrit() {
			return this.Inscrit;
		}

		public Integer Abstention;

		public Integer getAbstention() {
			return this.Abstention;
		}

		public BigDecimal PourcentageAbstention_Inscrit;

		public BigDecimal getPourcentageAbstention_Inscrit() {
			return this.PourcentageAbstention_Inscrit;
		}

		public Integer Votant;

		public Integer getVotant() {
			return this.Votant;
		}

		public BigDecimal PourcentageVotant_Inscrit;

		public BigDecimal getPourcentageVotant_Inscrit() {
			return this.PourcentageVotant_Inscrit;
		}

		public Integer VoteBlanc;

		public Integer getVoteBlanc() {
			return this.VoteBlanc;
		}

		public BigDecimal PourcentageBlanc_Inscrit;

		public BigDecimal getPourcentageBlanc_Inscrit() {
			return this.PourcentageBlanc_Inscrit;
		}

		public BigDecimal PourcentageBlanc_Votant;

		public BigDecimal getPourcentageBlanc_Votant() {
			return this.PourcentageBlanc_Votant;
		}

		public Integer VoteNul;

		public Integer getVoteNul() {
			return this.VoteNul;
		}

		public BigDecimal PourcentageNul_Inscrit;

		public BigDecimal getPourcentageNul_Inscrit() {
			return this.PourcentageNul_Inscrit;
		}

		public BigDecimal PourcentageNul_Votant;

		public BigDecimal getPourcentageNul_Votant() {
			return this.PourcentageNul_Votant;
		}

		public Integer Exprime;

		public Integer getExprime() {
			return this.Exprime;
		}

		public BigDecimal PourcentageExprime_Inscrit;

		public BigDecimal getPourcentageExprime_Inscrit() {
			return this.PourcentageExprime_Inscrit;
		}

		public BigDecimal PourcentageExprime_Votant;

		public BigDecimal getPourcentageExprime_Votant() {
			return this.PourcentageExprime_Votant;
		}

		public Integer NumeroPanneau;

		public Integer getNumeroPanneau() {
			return this.NumeroPanneau;
		}

		public String Sexe;

		public String getSexe() {
			return this.Sexe;
		}

		public String Nom;

		public String getNom() {
			return this.Nom;
		}

		public String Prenom;

		public String getPrenom() {
			return this.Prenom;
		}

		public Integer Voix;

		public Integer getVoix() {
			return this.Voix;
		}

		public BigDecimal PourcentageVoix_Inscrit;

		public BigDecimal getPourcentageVoix_Inscrit() {
			return this.PourcentageVoix_Inscrit;
		}

		public BigDecimal PourcentageVoix_Exprime;

		public BigDecimal getPourcentageVoix_Exprime() {
			return this.PourcentageVoix_Exprime;
		}

		public Integer NumeroPanneau1;

		public Integer getNumeroPanneau1() {
			return this.NumeroPanneau1;
		}

		public String Sexe1;

		public String getSexe1() {
			return this.Sexe1;
		}

		public String Nom1;

		public String getNom1() {
			return this.Nom1;
		}

		public String Prenom1;

		public String getPrenom1() {
			return this.Prenom1;
		}

		public Integer Voix1;

		public Integer getVoix1() {
			return this.Voix1;
		}

		public BigDecimal PourcentageVoix_Inscrit1;

		public BigDecimal getPourcentageVoix_Inscrit1() {
			return this.PourcentageVoix_Inscrit1;
		}

		public BigDecimal PourcentageVoix_Exprime1;

		public BigDecimal getPourcentageVoix_Exprime1() {
			return this.PourcentageVoix_Exprime1;
		}

		public Integer NumeroPanneau2;

		public Integer getNumeroPanneau2() {
			return this.NumeroPanneau2;
		}

		public String Sexe2;

		public String getSexe2() {
			return this.Sexe2;
		}

		public String Nom2;

		public String getNom2() {
			return this.Nom2;
		}

		public String Prenom2;

		public String getPrenom2() {
			return this.Prenom2;
		}

		public Integer Voix2;

		public Integer getVoix2() {
			return this.Voix2;
		}

		public BigDecimal PourcentageVoix_Inscrit2;

		public BigDecimal getPourcentageVoix_Inscrit2() {
			return this.PourcentageVoix_Inscrit2;
		}

		public BigDecimal PourcentageVoix_Exprime2;

		public BigDecimal getPourcentageVoix_Exprime2() {
			return this.PourcentageVoix_Exprime2;
		}

		public Integer NumeroPanneau3;

		public Integer getNumeroPanneau3() {
			return this.NumeroPanneau3;
		}

		public String Sexe3;

		public String getSexe3() {
			return this.Sexe3;
		}

		public String Nom3;

		public String getNom3() {
			return this.Nom3;
		}

		public String Prenom3;

		public String getPrenom3() {
			return this.Prenom3;
		}

		public Integer Voix3;

		public Integer getVoix3() {
			return this.Voix3;
		}

		public BigDecimal PourcentageVoix_Inscrit3;

		public BigDecimal getPourcentageVoix_Inscrit3() {
			return this.PourcentageVoix_Inscrit3;
		}

		public BigDecimal PourcentageVoix_Exprime3;

		public BigDecimal getPourcentageVoix_Exprime3() {
			return this.PourcentageVoix_Exprime3;
		}

		public Integer NumeroPanneau4;

		public Integer getNumeroPanneau4() {
			return this.NumeroPanneau4;
		}

		public String Sexe4;

		public String getSexe4() {
			return this.Sexe4;
		}

		public String Nom4;

		public String getNom4() {
			return this.Nom4;
		}

		public String Prenom4;

		public String getPrenom4() {
			return this.Prenom4;
		}

		public Integer Voix4;

		public Integer getVoix4() {
			return this.Voix4;
		}

		public BigDecimal PourcentageVoix_Inscrit4;

		public BigDecimal getPourcentageVoix_Inscrit4() {
			return this.PourcentageVoix_Inscrit4;
		}

		public BigDecimal PourcentageVoix_Exprime4;

		public BigDecimal getPourcentageVoix_Exprime4() {
			return this.PourcentageVoix_Exprime4;
		}

		public Integer NumeroPanneau5;

		public Integer getNumeroPanneau5() {
			return this.NumeroPanneau5;
		}

		public String Sexe5;

		public String getSexe5() {
			return this.Sexe5;
		}

		public String Nom5;

		public String getNom5() {
			return this.Nom5;
		}

		public String Prenom5;

		public String getPrenom5() {
			return this.Prenom5;
		}

		public Integer Voix5;

		public Integer getVoix5() {
			return this.Voix5;
		}

		public BigDecimal PourcentageVoix_Inscrit5;

		public BigDecimal getPourcentageVoix_Inscrit5() {
			return this.PourcentageVoix_Inscrit5;
		}

		public BigDecimal PourcentageVoix_Exprime5;

		public BigDecimal getPourcentageVoix_Exprime5() {
			return this.PourcentageVoix_Exprime5;
		}

		public Integer NumeroPanneau6;

		public Integer getNumeroPanneau6() {
			return this.NumeroPanneau6;
		}

		public String Sexe6;

		public String getSexe6() {
			return this.Sexe6;
		}

		public String Nom6;

		public String getNom6() {
			return this.Nom6;
		}

		public String Prenom6;

		public String getPrenom6() {
			return this.Prenom6;
		}

		public Integer Voix6;

		public Integer getVoix6() {
			return this.Voix6;
		}

		public BigDecimal PourcentageVoix_Inscrit6;

		public BigDecimal getPourcentageVoix_Inscrit6() {
			return this.PourcentageVoix_Inscrit6;
		}

		public BigDecimal PourcentageVoix_Exprime6;

		public BigDecimal getPourcentageVoix_Exprime6() {
			return this.PourcentageVoix_Exprime6;
		}

		public Integer NumeroPanneau7;

		public Integer getNumeroPanneau7() {
			return this.NumeroPanneau7;
		}

		public String Sexe7;

		public String getSexe7() {
			return this.Sexe7;
		}

		public String Nom7;

		public String getNom7() {
			return this.Nom7;
		}

		public String Prenom7;

		public String getPrenom7() {
			return this.Prenom7;
		}

		public Integer Voix7;

		public Integer getVoix7() {
			return this.Voix7;
		}

		public BigDecimal PourcentageVoix_Inscrit7;

		public BigDecimal getPourcentageVoix_Inscrit7() {
			return this.PourcentageVoix_Inscrit7;
		}

		public BigDecimal PourcentageVoix_Exprime7;

		public BigDecimal getPourcentageVoix_Exprime7() {
			return this.PourcentageVoix_Exprime7;
		}

		public Integer NumeroPanneau8;

		public Integer getNumeroPanneau8() {
			return this.NumeroPanneau8;
		}

		public String Sexe8;

		public String getSexe8() {
			return this.Sexe8;
		}

		public String Nom8;

		public String getNom8() {
			return this.Nom8;
		}

		public String Prenom8;

		public String getPrenom8() {
			return this.Prenom8;
		}

		public Integer Voix8;

		public Integer getVoix8() {
			return this.Voix8;
		}

		public BigDecimal PourcentageVoix_Inscrit8;

		public BigDecimal getPourcentageVoix_Inscrit8() {
			return this.PourcentageVoix_Inscrit8;
		}

		public BigDecimal PourcentageVoix_Exprime8;

		public BigDecimal getPourcentageVoix_Exprime8() {
			return this.PourcentageVoix_Exprime8;
		}

		public Integer NumeroPanneau9;

		public Integer getNumeroPanneau9() {
			return this.NumeroPanneau9;
		}

		public String Sexe9;

		public String getSexe9() {
			return this.Sexe9;
		}

		public String Nom9;

		public String getNom9() {
			return this.Nom9;
		}

		public String Prenom9;

		public String getPrenom9() {
			return this.Prenom9;
		}

		public Integer Voix9;

		public Integer getVoix9() {
			return this.Voix9;
		}

		public BigDecimal PourcentageVoix_Inscrit9;

		public BigDecimal getPourcentageVoix_Inscrit9() {
			return this.PourcentageVoix_Inscrit9;
		}

		public BigDecimal PourcentageVoix_Exprime9;

		public BigDecimal getPourcentageVoix_Exprime9() {
			return this.PourcentageVoix_Exprime9;
		}

		public Integer NumeroPanneau10;

		public Integer getNumeroPanneau10() {
			return this.NumeroPanneau10;
		}

		public String Sexe10;

		public String getSexe10() {
			return this.Sexe10;
		}

		public String Nom10;

		public String getNom10() {
			return this.Nom10;
		}

		public String Prenom10;

		public String getPrenom10() {
			return this.Prenom10;
		}

		public Integer Voix10;

		public Integer getVoix10() {
			return this.Voix10;
		}

		public BigDecimal PourcentageVoix_Inscrit10;

		public BigDecimal getPourcentageVoix_Inscrit10() {
			return this.PourcentageVoix_Inscrit10;
		}

		public BigDecimal PourcentageVoix_Exprime10;

		public BigDecimal getPourcentageVoix_Exprime10() {
			return this.PourcentageVoix_Exprime10;
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
			other.CodeDepartement = this.CodeDepartement;
			other.LibelleDepartement = this.LibelleDepartement;
			other.CodeCirconscription = this.CodeCirconscription;
			other.LibelleCirconscription = this.LibelleCirconscription;
			other.Inscrit = this.Inscrit;
			other.Abstention = this.Abstention;
			other.PourcentageAbstention_Inscrit = this.PourcentageAbstention_Inscrit;
			other.Votant = this.Votant;
			other.PourcentageVotant_Inscrit = this.PourcentageVotant_Inscrit;
			other.VoteBlanc = this.VoteBlanc;
			other.PourcentageBlanc_Inscrit = this.PourcentageBlanc_Inscrit;
			other.PourcentageBlanc_Votant = this.PourcentageBlanc_Votant;
			other.VoteNul = this.VoteNul;
			other.PourcentageNul_Inscrit = this.PourcentageNul_Inscrit;
			other.PourcentageNul_Votant = this.PourcentageNul_Votant;
			other.Exprime = this.Exprime;
			other.PourcentageExprime_Inscrit = this.PourcentageExprime_Inscrit;
			other.PourcentageExprime_Votant = this.PourcentageExprime_Votant;
			other.NumeroPanneau = this.NumeroPanneau;
			other.Sexe = this.Sexe;
			other.Nom = this.Nom;
			other.Prenom = this.Prenom;
			other.Voix = this.Voix;
			other.PourcentageVoix_Inscrit = this.PourcentageVoix_Inscrit;
			other.PourcentageVoix_Exprime = this.PourcentageVoix_Exprime;
			other.NumeroPanneau1 = this.NumeroPanneau1;
			other.Sexe1 = this.Sexe1;
			other.Nom1 = this.Nom1;
			other.Prenom1 = this.Prenom1;
			other.Voix1 = this.Voix1;
			other.PourcentageVoix_Inscrit1 = this.PourcentageVoix_Inscrit1;
			other.PourcentageVoix_Exprime1 = this.PourcentageVoix_Exprime1;
			other.NumeroPanneau2 = this.NumeroPanneau2;
			other.Sexe2 = this.Sexe2;
			other.Nom2 = this.Nom2;
			other.Prenom2 = this.Prenom2;
			other.Voix2 = this.Voix2;
			other.PourcentageVoix_Inscrit2 = this.PourcentageVoix_Inscrit2;
			other.PourcentageVoix_Exprime2 = this.PourcentageVoix_Exprime2;
			other.NumeroPanneau3 = this.NumeroPanneau3;
			other.Sexe3 = this.Sexe3;
			other.Nom3 = this.Nom3;
			other.Prenom3 = this.Prenom3;
			other.Voix3 = this.Voix3;
			other.PourcentageVoix_Inscrit3 = this.PourcentageVoix_Inscrit3;
			other.PourcentageVoix_Exprime3 = this.PourcentageVoix_Exprime3;
			other.NumeroPanneau4 = this.NumeroPanneau4;
			other.Sexe4 = this.Sexe4;
			other.Nom4 = this.Nom4;
			other.Prenom4 = this.Prenom4;
			other.Voix4 = this.Voix4;
			other.PourcentageVoix_Inscrit4 = this.PourcentageVoix_Inscrit4;
			other.PourcentageVoix_Exprime4 = this.PourcentageVoix_Exprime4;
			other.NumeroPanneau5 = this.NumeroPanneau5;
			other.Sexe5 = this.Sexe5;
			other.Nom5 = this.Nom5;
			other.Prenom5 = this.Prenom5;
			other.Voix5 = this.Voix5;
			other.PourcentageVoix_Inscrit5 = this.PourcentageVoix_Inscrit5;
			other.PourcentageVoix_Exprime5 = this.PourcentageVoix_Exprime5;
			other.NumeroPanneau6 = this.NumeroPanneau6;
			other.Sexe6 = this.Sexe6;
			other.Nom6 = this.Nom6;
			other.Prenom6 = this.Prenom6;
			other.Voix6 = this.Voix6;
			other.PourcentageVoix_Inscrit6 = this.PourcentageVoix_Inscrit6;
			other.PourcentageVoix_Exprime6 = this.PourcentageVoix_Exprime6;
			other.NumeroPanneau7 = this.NumeroPanneau7;
			other.Sexe7 = this.Sexe7;
			other.Nom7 = this.Nom7;
			other.Prenom7 = this.Prenom7;
			other.Voix7 = this.Voix7;
			other.PourcentageVoix_Inscrit7 = this.PourcentageVoix_Inscrit7;
			other.PourcentageVoix_Exprime7 = this.PourcentageVoix_Exprime7;
			other.NumeroPanneau8 = this.NumeroPanneau8;
			other.Sexe8 = this.Sexe8;
			other.Nom8 = this.Nom8;
			other.Prenom8 = this.Prenom8;
			other.Voix8 = this.Voix8;
			other.PourcentageVoix_Inscrit8 = this.PourcentageVoix_Inscrit8;
			other.PourcentageVoix_Exprime8 = this.PourcentageVoix_Exprime8;
			other.NumeroPanneau9 = this.NumeroPanneau9;
			other.Sexe9 = this.Sexe9;
			other.Nom9 = this.Nom9;
			other.Prenom9 = this.Prenom9;
			other.Voix9 = this.Voix9;
			other.PourcentageVoix_Inscrit9 = this.PourcentageVoix_Inscrit9;
			other.PourcentageVoix_Exprime9 = this.PourcentageVoix_Exprime9;
			other.NumeroPanneau10 = this.NumeroPanneau10;
			other.Sexe10 = this.Sexe10;
			other.Nom10 = this.Nom10;
			other.Prenom10 = this.Prenom10;
			other.Voix10 = this.Voix10;
			other.PourcentageVoix_Inscrit10 = this.PourcentageVoix_Inscrit10;
			other.PourcentageVoix_Exprime10 = this.PourcentageVoix_Exprime10;

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
				if (length > commonByteArray_DATAMSPR_VoteElection2017.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_VoteElection2017.length == 0) {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_VoteElection2017, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_VoteElection2017, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_VoteElection2017.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_VoteElection2017.length == 0) {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_VoteElection2017, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_VoteElection2017, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_VoteElection2017) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.CodeDepartement = readString(dis);

					this.LibelleDepartement = readString(dis);

					this.CodeCirconscription = readString(dis);

					this.LibelleCirconscription = readString(dis);

					this.Inscrit = readInteger(dis);

					this.Abstention = readInteger(dis);

					this.PourcentageAbstention_Inscrit = (BigDecimal) dis.readObject();

					this.Votant = readInteger(dis);

					this.PourcentageVotant_Inscrit = (BigDecimal) dis.readObject();

					this.VoteBlanc = readInteger(dis);

					this.PourcentageBlanc_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageBlanc_Votant = (BigDecimal) dis.readObject();

					this.VoteNul = readInteger(dis);

					this.PourcentageNul_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageNul_Votant = (BigDecimal) dis.readObject();

					this.Exprime = readInteger(dis);

					this.PourcentageExprime_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageExprime_Votant = (BigDecimal) dis.readObject();

					this.NumeroPanneau = readInteger(dis);

					this.Sexe = readString(dis);

					this.Nom = readString(dis);

					this.Prenom = readString(dis);

					this.Voix = readInteger(dis);

					this.PourcentageVoix_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime = (BigDecimal) dis.readObject();

					this.NumeroPanneau1 = readInteger(dis);

					this.Sexe1 = readString(dis);

					this.Nom1 = readString(dis);

					this.Prenom1 = readString(dis);

					this.Voix1 = readInteger(dis);

					this.PourcentageVoix_Inscrit1 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime1 = (BigDecimal) dis.readObject();

					this.NumeroPanneau2 = readInteger(dis);

					this.Sexe2 = readString(dis);

					this.Nom2 = readString(dis);

					this.Prenom2 = readString(dis);

					this.Voix2 = readInteger(dis);

					this.PourcentageVoix_Inscrit2 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime2 = (BigDecimal) dis.readObject();

					this.NumeroPanneau3 = readInteger(dis);

					this.Sexe3 = readString(dis);

					this.Nom3 = readString(dis);

					this.Prenom3 = readString(dis);

					this.Voix3 = readInteger(dis);

					this.PourcentageVoix_Inscrit3 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime3 = (BigDecimal) dis.readObject();

					this.NumeroPanneau4 = readInteger(dis);

					this.Sexe4 = readString(dis);

					this.Nom4 = readString(dis);

					this.Prenom4 = readString(dis);

					this.Voix4 = readInteger(dis);

					this.PourcentageVoix_Inscrit4 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime4 = (BigDecimal) dis.readObject();

					this.NumeroPanneau5 = readInteger(dis);

					this.Sexe5 = readString(dis);

					this.Nom5 = readString(dis);

					this.Prenom5 = readString(dis);

					this.Voix5 = readInteger(dis);

					this.PourcentageVoix_Inscrit5 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime5 = (BigDecimal) dis.readObject();

					this.NumeroPanneau6 = readInteger(dis);

					this.Sexe6 = readString(dis);

					this.Nom6 = readString(dis);

					this.Prenom6 = readString(dis);

					this.Voix6 = readInteger(dis);

					this.PourcentageVoix_Inscrit6 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime6 = (BigDecimal) dis.readObject();

					this.NumeroPanneau7 = readInteger(dis);

					this.Sexe7 = readString(dis);

					this.Nom7 = readString(dis);

					this.Prenom7 = readString(dis);

					this.Voix7 = readInteger(dis);

					this.PourcentageVoix_Inscrit7 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime7 = (BigDecimal) dis.readObject();

					this.NumeroPanneau8 = readInteger(dis);

					this.Sexe8 = readString(dis);

					this.Nom8 = readString(dis);

					this.Prenom8 = readString(dis);

					this.Voix8 = readInteger(dis);

					this.PourcentageVoix_Inscrit8 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime8 = (BigDecimal) dis.readObject();

					this.NumeroPanneau9 = readInteger(dis);

					this.Sexe9 = readString(dis);

					this.Nom9 = readString(dis);

					this.Prenom9 = readString(dis);

					this.Voix9 = readInteger(dis);

					this.PourcentageVoix_Inscrit9 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime9 = (BigDecimal) dis.readObject();

					this.NumeroPanneau10 = readInteger(dis);

					this.Sexe10 = readString(dis);

					this.Nom10 = readString(dis);

					this.Prenom10 = readString(dis);

					this.Voix10 = readInteger(dis);

					this.PourcentageVoix_Inscrit10 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime10 = (BigDecimal) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_VoteElection2017) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.CodeDepartement = readString(dis);

					this.LibelleDepartement = readString(dis);

					this.CodeCirconscription = readString(dis);

					this.LibelleCirconscription = readString(dis);

					this.Inscrit = readInteger(dis);

					this.Abstention = readInteger(dis);

					this.PourcentageAbstention_Inscrit = (BigDecimal) dis.readObject();

					this.Votant = readInteger(dis);

					this.PourcentageVotant_Inscrit = (BigDecimal) dis.readObject();

					this.VoteBlanc = readInteger(dis);

					this.PourcentageBlanc_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageBlanc_Votant = (BigDecimal) dis.readObject();

					this.VoteNul = readInteger(dis);

					this.PourcentageNul_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageNul_Votant = (BigDecimal) dis.readObject();

					this.Exprime = readInteger(dis);

					this.PourcentageExprime_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageExprime_Votant = (BigDecimal) dis.readObject();

					this.NumeroPanneau = readInteger(dis);

					this.Sexe = readString(dis);

					this.Nom = readString(dis);

					this.Prenom = readString(dis);

					this.Voix = readInteger(dis);

					this.PourcentageVoix_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime = (BigDecimal) dis.readObject();

					this.NumeroPanneau1 = readInteger(dis);

					this.Sexe1 = readString(dis);

					this.Nom1 = readString(dis);

					this.Prenom1 = readString(dis);

					this.Voix1 = readInteger(dis);

					this.PourcentageVoix_Inscrit1 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime1 = (BigDecimal) dis.readObject();

					this.NumeroPanneau2 = readInteger(dis);

					this.Sexe2 = readString(dis);

					this.Nom2 = readString(dis);

					this.Prenom2 = readString(dis);

					this.Voix2 = readInteger(dis);

					this.PourcentageVoix_Inscrit2 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime2 = (BigDecimal) dis.readObject();

					this.NumeroPanneau3 = readInteger(dis);

					this.Sexe3 = readString(dis);

					this.Nom3 = readString(dis);

					this.Prenom3 = readString(dis);

					this.Voix3 = readInteger(dis);

					this.PourcentageVoix_Inscrit3 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime3 = (BigDecimal) dis.readObject();

					this.NumeroPanneau4 = readInteger(dis);

					this.Sexe4 = readString(dis);

					this.Nom4 = readString(dis);

					this.Prenom4 = readString(dis);

					this.Voix4 = readInteger(dis);

					this.PourcentageVoix_Inscrit4 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime4 = (BigDecimal) dis.readObject();

					this.NumeroPanneau5 = readInteger(dis);

					this.Sexe5 = readString(dis);

					this.Nom5 = readString(dis);

					this.Prenom5 = readString(dis);

					this.Voix5 = readInteger(dis);

					this.PourcentageVoix_Inscrit5 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime5 = (BigDecimal) dis.readObject();

					this.NumeroPanneau6 = readInteger(dis);

					this.Sexe6 = readString(dis);

					this.Nom6 = readString(dis);

					this.Prenom6 = readString(dis);

					this.Voix6 = readInteger(dis);

					this.PourcentageVoix_Inscrit6 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime6 = (BigDecimal) dis.readObject();

					this.NumeroPanneau7 = readInteger(dis);

					this.Sexe7 = readString(dis);

					this.Nom7 = readString(dis);

					this.Prenom7 = readString(dis);

					this.Voix7 = readInteger(dis);

					this.PourcentageVoix_Inscrit7 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime7 = (BigDecimal) dis.readObject();

					this.NumeroPanneau8 = readInteger(dis);

					this.Sexe8 = readString(dis);

					this.Nom8 = readString(dis);

					this.Prenom8 = readString(dis);

					this.Voix8 = readInteger(dis);

					this.PourcentageVoix_Inscrit8 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime8 = (BigDecimal) dis.readObject();

					this.NumeroPanneau9 = readInteger(dis);

					this.Sexe9 = readString(dis);

					this.Nom9 = readString(dis);

					this.Prenom9 = readString(dis);

					this.Voix9 = readInteger(dis);

					this.PourcentageVoix_Inscrit9 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime9 = (BigDecimal) dis.readObject();

					this.NumeroPanneau10 = readInteger(dis);

					this.Sexe10 = readString(dis);

					this.Nom10 = readString(dis);

					this.Prenom10 = readString(dis);

					this.Voix10 = readInteger(dis);

					this.PourcentageVoix_Inscrit10 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime10 = (BigDecimal) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.CodeDepartement, dos);

				// String

				writeString(this.LibelleDepartement, dos);

				// String

				writeString(this.CodeCirconscription, dos);

				// String

				writeString(this.LibelleCirconscription, dos);

				// Integer

				writeInteger(this.Inscrit, dos);

				// Integer

				writeInteger(this.Abstention, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageAbstention_Inscrit);

				// Integer

				writeInteger(this.Votant, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVotant_Inscrit);

				// Integer

				writeInteger(this.VoteBlanc, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Votant);

				// Integer

				writeInteger(this.VoteNul, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Votant);

				// Integer

				writeInteger(this.Exprime, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Votant);

				// Integer

				writeInteger(this.NumeroPanneau, dos);

				// String

				writeString(this.Sexe, dos);

				// String

				writeString(this.Nom, dos);

				// String

				writeString(this.Prenom, dos);

				// Integer

				writeInteger(this.Voix, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime);

				// Integer

				writeInteger(this.NumeroPanneau1, dos);

				// String

				writeString(this.Sexe1, dos);

				// String

				writeString(this.Nom1, dos);

				// String

				writeString(this.Prenom1, dos);

				// Integer

				writeInteger(this.Voix1, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit1);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime1);

				// Integer

				writeInteger(this.NumeroPanneau2, dos);

				// String

				writeString(this.Sexe2, dos);

				// String

				writeString(this.Nom2, dos);

				// String

				writeString(this.Prenom2, dos);

				// Integer

				writeInteger(this.Voix2, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit2);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime2);

				// Integer

				writeInteger(this.NumeroPanneau3, dos);

				// String

				writeString(this.Sexe3, dos);

				// String

				writeString(this.Nom3, dos);

				// String

				writeString(this.Prenom3, dos);

				// Integer

				writeInteger(this.Voix3, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit3);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime3);

				// Integer

				writeInteger(this.NumeroPanneau4, dos);

				// String

				writeString(this.Sexe4, dos);

				// String

				writeString(this.Nom4, dos);

				// String

				writeString(this.Prenom4, dos);

				// Integer

				writeInteger(this.Voix4, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit4);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime4);

				// Integer

				writeInteger(this.NumeroPanneau5, dos);

				// String

				writeString(this.Sexe5, dos);

				// String

				writeString(this.Nom5, dos);

				// String

				writeString(this.Prenom5, dos);

				// Integer

				writeInteger(this.Voix5, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit5);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime5);

				// Integer

				writeInteger(this.NumeroPanneau6, dos);

				// String

				writeString(this.Sexe6, dos);

				// String

				writeString(this.Nom6, dos);

				// String

				writeString(this.Prenom6, dos);

				// Integer

				writeInteger(this.Voix6, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit6);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime6);

				// Integer

				writeInteger(this.NumeroPanneau7, dos);

				// String

				writeString(this.Sexe7, dos);

				// String

				writeString(this.Nom7, dos);

				// String

				writeString(this.Prenom7, dos);

				// Integer

				writeInteger(this.Voix7, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit7);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime7);

				// Integer

				writeInteger(this.NumeroPanneau8, dos);

				// String

				writeString(this.Sexe8, dos);

				// String

				writeString(this.Nom8, dos);

				// String

				writeString(this.Prenom8, dos);

				// Integer

				writeInteger(this.Voix8, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit8);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime8);

				// Integer

				writeInteger(this.NumeroPanneau9, dos);

				// String

				writeString(this.Sexe9, dos);

				// String

				writeString(this.Nom9, dos);

				// String

				writeString(this.Prenom9, dos);

				// Integer

				writeInteger(this.Voix9, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit9);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime9);

				// Integer

				writeInteger(this.NumeroPanneau10, dos);

				// String

				writeString(this.Sexe10, dos);

				// String

				writeString(this.Nom10, dos);

				// String

				writeString(this.Prenom10, dos);

				// Integer

				writeInteger(this.Voix10, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit10);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime10);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.CodeDepartement, dos);

				// String

				writeString(this.LibelleDepartement, dos);

				// String

				writeString(this.CodeCirconscription, dos);

				// String

				writeString(this.LibelleCirconscription, dos);

				// Integer

				writeInteger(this.Inscrit, dos);

				// Integer

				writeInteger(this.Abstention, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageAbstention_Inscrit);

				// Integer

				writeInteger(this.Votant, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVotant_Inscrit);

				// Integer

				writeInteger(this.VoteBlanc, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Votant);

				// Integer

				writeInteger(this.VoteNul, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Votant);

				// Integer

				writeInteger(this.Exprime, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Votant);

				// Integer

				writeInteger(this.NumeroPanneau, dos);

				// String

				writeString(this.Sexe, dos);

				// String

				writeString(this.Nom, dos);

				// String

				writeString(this.Prenom, dos);

				// Integer

				writeInteger(this.Voix, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime);

				// Integer

				writeInteger(this.NumeroPanneau1, dos);

				// String

				writeString(this.Sexe1, dos);

				// String

				writeString(this.Nom1, dos);

				// String

				writeString(this.Prenom1, dos);

				// Integer

				writeInteger(this.Voix1, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit1);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime1);

				// Integer

				writeInteger(this.NumeroPanneau2, dos);

				// String

				writeString(this.Sexe2, dos);

				// String

				writeString(this.Nom2, dos);

				// String

				writeString(this.Prenom2, dos);

				// Integer

				writeInteger(this.Voix2, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit2);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime2);

				// Integer

				writeInteger(this.NumeroPanneau3, dos);

				// String

				writeString(this.Sexe3, dos);

				// String

				writeString(this.Nom3, dos);

				// String

				writeString(this.Prenom3, dos);

				// Integer

				writeInteger(this.Voix3, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit3);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime3);

				// Integer

				writeInteger(this.NumeroPanneau4, dos);

				// String

				writeString(this.Sexe4, dos);

				// String

				writeString(this.Nom4, dos);

				// String

				writeString(this.Prenom4, dos);

				// Integer

				writeInteger(this.Voix4, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit4);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime4);

				// Integer

				writeInteger(this.NumeroPanneau5, dos);

				// String

				writeString(this.Sexe5, dos);

				// String

				writeString(this.Nom5, dos);

				// String

				writeString(this.Prenom5, dos);

				// Integer

				writeInteger(this.Voix5, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit5);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime5);

				// Integer

				writeInteger(this.NumeroPanneau6, dos);

				// String

				writeString(this.Sexe6, dos);

				// String

				writeString(this.Nom6, dos);

				// String

				writeString(this.Prenom6, dos);

				// Integer

				writeInteger(this.Voix6, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit6);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime6);

				// Integer

				writeInteger(this.NumeroPanneau7, dos);

				// String

				writeString(this.Sexe7, dos);

				// String

				writeString(this.Nom7, dos);

				// String

				writeString(this.Prenom7, dos);

				// Integer

				writeInteger(this.Voix7, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit7);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime7);

				// Integer

				writeInteger(this.NumeroPanneau8, dos);

				// String

				writeString(this.Sexe8, dos);

				// String

				writeString(this.Nom8, dos);

				// String

				writeString(this.Prenom8, dos);

				// Integer

				writeInteger(this.Voix8, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit8);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime8);

				// Integer

				writeInteger(this.NumeroPanneau9, dos);

				// String

				writeString(this.Sexe9, dos);

				// String

				writeString(this.Nom9, dos);

				// String

				writeString(this.Prenom9, dos);

				// Integer

				writeInteger(this.Voix9, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit9);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime9);

				// Integer

				writeInteger(this.NumeroPanneau10, dos);

				// String

				writeString(this.Sexe10, dos);

				// String

				writeString(this.Nom10, dos);

				// String

				writeString(this.Prenom10, dos);

				// Integer

				writeInteger(this.Voix10, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit10);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime10);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",CodeDepartement=" + CodeDepartement);
			sb.append(",LibelleDepartement=" + LibelleDepartement);
			sb.append(",CodeCirconscription=" + CodeCirconscription);
			sb.append(",LibelleCirconscription=" + LibelleCirconscription);
			sb.append(",Inscrit=" + String.valueOf(Inscrit));
			sb.append(",Abstention=" + String.valueOf(Abstention));
			sb.append(",PourcentageAbstention_Inscrit=" + String.valueOf(PourcentageAbstention_Inscrit));
			sb.append(",Votant=" + String.valueOf(Votant));
			sb.append(",PourcentageVotant_Inscrit=" + String.valueOf(PourcentageVotant_Inscrit));
			sb.append(",VoteBlanc=" + String.valueOf(VoteBlanc));
			sb.append(",PourcentageBlanc_Inscrit=" + String.valueOf(PourcentageBlanc_Inscrit));
			sb.append(",PourcentageBlanc_Votant=" + String.valueOf(PourcentageBlanc_Votant));
			sb.append(",VoteNul=" + String.valueOf(VoteNul));
			sb.append(",PourcentageNul_Inscrit=" + String.valueOf(PourcentageNul_Inscrit));
			sb.append(",PourcentageNul_Votant=" + String.valueOf(PourcentageNul_Votant));
			sb.append(",Exprime=" + String.valueOf(Exprime));
			sb.append(",PourcentageExprime_Inscrit=" + String.valueOf(PourcentageExprime_Inscrit));
			sb.append(",PourcentageExprime_Votant=" + String.valueOf(PourcentageExprime_Votant));
			sb.append(",NumeroPanneau=" + String.valueOf(NumeroPanneau));
			sb.append(",Sexe=" + Sexe);
			sb.append(",Nom=" + Nom);
			sb.append(",Prenom=" + Prenom);
			sb.append(",Voix=" + String.valueOf(Voix));
			sb.append(",PourcentageVoix_Inscrit=" + String.valueOf(PourcentageVoix_Inscrit));
			sb.append(",PourcentageVoix_Exprime=" + String.valueOf(PourcentageVoix_Exprime));
			sb.append(",NumeroPanneau1=" + String.valueOf(NumeroPanneau1));
			sb.append(",Sexe1=" + Sexe1);
			sb.append(",Nom1=" + Nom1);
			sb.append(",Prenom1=" + Prenom1);
			sb.append(",Voix1=" + String.valueOf(Voix1));
			sb.append(",PourcentageVoix_Inscrit1=" + String.valueOf(PourcentageVoix_Inscrit1));
			sb.append(",PourcentageVoix_Exprime1=" + String.valueOf(PourcentageVoix_Exprime1));
			sb.append(",NumeroPanneau2=" + String.valueOf(NumeroPanneau2));
			sb.append(",Sexe2=" + Sexe2);
			sb.append(",Nom2=" + Nom2);
			sb.append(",Prenom2=" + Prenom2);
			sb.append(",Voix2=" + String.valueOf(Voix2));
			sb.append(",PourcentageVoix_Inscrit2=" + String.valueOf(PourcentageVoix_Inscrit2));
			sb.append(",PourcentageVoix_Exprime2=" + String.valueOf(PourcentageVoix_Exprime2));
			sb.append(",NumeroPanneau3=" + String.valueOf(NumeroPanneau3));
			sb.append(",Sexe3=" + Sexe3);
			sb.append(",Nom3=" + Nom3);
			sb.append(",Prenom3=" + Prenom3);
			sb.append(",Voix3=" + String.valueOf(Voix3));
			sb.append(",PourcentageVoix_Inscrit3=" + String.valueOf(PourcentageVoix_Inscrit3));
			sb.append(",PourcentageVoix_Exprime3=" + String.valueOf(PourcentageVoix_Exprime3));
			sb.append(",NumeroPanneau4=" + String.valueOf(NumeroPanneau4));
			sb.append(",Sexe4=" + Sexe4);
			sb.append(",Nom4=" + Nom4);
			sb.append(",Prenom4=" + Prenom4);
			sb.append(",Voix4=" + String.valueOf(Voix4));
			sb.append(",PourcentageVoix_Inscrit4=" + String.valueOf(PourcentageVoix_Inscrit4));
			sb.append(",PourcentageVoix_Exprime4=" + String.valueOf(PourcentageVoix_Exprime4));
			sb.append(",NumeroPanneau5=" + String.valueOf(NumeroPanneau5));
			sb.append(",Sexe5=" + Sexe5);
			sb.append(",Nom5=" + Nom5);
			sb.append(",Prenom5=" + Prenom5);
			sb.append(",Voix5=" + String.valueOf(Voix5));
			sb.append(",PourcentageVoix_Inscrit5=" + String.valueOf(PourcentageVoix_Inscrit5));
			sb.append(",PourcentageVoix_Exprime5=" + String.valueOf(PourcentageVoix_Exprime5));
			sb.append(",NumeroPanneau6=" + String.valueOf(NumeroPanneau6));
			sb.append(",Sexe6=" + Sexe6);
			sb.append(",Nom6=" + Nom6);
			sb.append(",Prenom6=" + Prenom6);
			sb.append(",Voix6=" + String.valueOf(Voix6));
			sb.append(",PourcentageVoix_Inscrit6=" + String.valueOf(PourcentageVoix_Inscrit6));
			sb.append(",PourcentageVoix_Exprime6=" + String.valueOf(PourcentageVoix_Exprime6));
			sb.append(",NumeroPanneau7=" + String.valueOf(NumeroPanneau7));
			sb.append(",Sexe7=" + Sexe7);
			sb.append(",Nom7=" + Nom7);
			sb.append(",Prenom7=" + Prenom7);
			sb.append(",Voix7=" + String.valueOf(Voix7));
			sb.append(",PourcentageVoix_Inscrit7=" + String.valueOf(PourcentageVoix_Inscrit7));
			sb.append(",PourcentageVoix_Exprime7=" + String.valueOf(PourcentageVoix_Exprime7));
			sb.append(",NumeroPanneau8=" + String.valueOf(NumeroPanneau8));
			sb.append(",Sexe8=" + Sexe8);
			sb.append(",Nom8=" + Nom8);
			sb.append(",Prenom8=" + Prenom8);
			sb.append(",Voix8=" + String.valueOf(Voix8));
			sb.append(",PourcentageVoix_Inscrit8=" + String.valueOf(PourcentageVoix_Inscrit8));
			sb.append(",PourcentageVoix_Exprime8=" + String.valueOf(PourcentageVoix_Exprime8));
			sb.append(",NumeroPanneau9=" + String.valueOf(NumeroPanneau9));
			sb.append(",Sexe9=" + Sexe9);
			sb.append(",Nom9=" + Nom9);
			sb.append(",Prenom9=" + Prenom9);
			sb.append(",Voix9=" + String.valueOf(Voix9));
			sb.append(",PourcentageVoix_Inscrit9=" + String.valueOf(PourcentageVoix_Inscrit9));
			sb.append(",PourcentageVoix_Exprime9=" + String.valueOf(PourcentageVoix_Exprime9));
			sb.append(",NumeroPanneau10=" + String.valueOf(NumeroPanneau10));
			sb.append(",Sexe10=" + Sexe10);
			sb.append(",Nom10=" + Nom10);
			sb.append(",Prenom10=" + Prenom10);
			sb.append(",Voix10=" + String.valueOf(Voix10));
			sb.append(",PourcentageVoix_Inscrit10=" + String.valueOf(PourcentageVoix_Inscrit10));
			sb.append(",PourcentageVoix_Exprime10=" + String.valueOf(PourcentageVoix_Exprime10));
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

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_DATAMSPR_VoteElection2017 = new byte[0];
		static byte[] commonByteArray_DATAMSPR_VoteElection2017 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public String CodeDepartement;

		public String getCodeDepartement() {
			return this.CodeDepartement;
		}

		public String LibelleDepartement;

		public String getLibelleDepartement() {
			return this.LibelleDepartement;
		}

		public String CodeCirconscription;

		public String getCodeCirconscription() {
			return this.CodeCirconscription;
		}

		public String LibelleCirconscription;

		public String getLibelleCirconscription() {
			return this.LibelleCirconscription;
		}

		public Integer Inscrit;

		public Integer getInscrit() {
			return this.Inscrit;
		}

		public Integer Abstention;

		public Integer getAbstention() {
			return this.Abstention;
		}

		public BigDecimal PourcentageAbstention_Inscrit;

		public BigDecimal getPourcentageAbstention_Inscrit() {
			return this.PourcentageAbstention_Inscrit;
		}

		public Integer Votant;

		public Integer getVotant() {
			return this.Votant;
		}

		public BigDecimal PourcentageVotant_Inscrit;

		public BigDecimal getPourcentageVotant_Inscrit() {
			return this.PourcentageVotant_Inscrit;
		}

		public Integer VoteBlanc;

		public Integer getVoteBlanc() {
			return this.VoteBlanc;
		}

		public BigDecimal PourcentageBlanc_Inscrit;

		public BigDecimal getPourcentageBlanc_Inscrit() {
			return this.PourcentageBlanc_Inscrit;
		}

		public BigDecimal PourcentageBlanc_Votant;

		public BigDecimal getPourcentageBlanc_Votant() {
			return this.PourcentageBlanc_Votant;
		}

		public Integer VoteNul;

		public Integer getVoteNul() {
			return this.VoteNul;
		}

		public BigDecimal PourcentageNul_Inscrit;

		public BigDecimal getPourcentageNul_Inscrit() {
			return this.PourcentageNul_Inscrit;
		}

		public BigDecimal PourcentageNul_Votant;

		public BigDecimal getPourcentageNul_Votant() {
			return this.PourcentageNul_Votant;
		}

		public Integer Exprime;

		public Integer getExprime() {
			return this.Exprime;
		}

		public BigDecimal PourcentageExprime_Inscrit;

		public BigDecimal getPourcentageExprime_Inscrit() {
			return this.PourcentageExprime_Inscrit;
		}

		public BigDecimal PourcentageExprime_Votant;

		public BigDecimal getPourcentageExprime_Votant() {
			return this.PourcentageExprime_Votant;
		}

		public Integer NumeroPanneau;

		public Integer getNumeroPanneau() {
			return this.NumeroPanneau;
		}

		public String Sexe;

		public String getSexe() {
			return this.Sexe;
		}

		public String Nom;

		public String getNom() {
			return this.Nom;
		}

		public String Prenom;

		public String getPrenom() {
			return this.Prenom;
		}

		public Integer Voix;

		public Integer getVoix() {
			return this.Voix;
		}

		public BigDecimal PourcentageVoix_Inscrit;

		public BigDecimal getPourcentageVoix_Inscrit() {
			return this.PourcentageVoix_Inscrit;
		}

		public BigDecimal PourcentageVoix_Exprime;

		public BigDecimal getPourcentageVoix_Exprime() {
			return this.PourcentageVoix_Exprime;
		}

		public Integer NumeroPanneau1;

		public Integer getNumeroPanneau1() {
			return this.NumeroPanneau1;
		}

		public String Sexe1;

		public String getSexe1() {
			return this.Sexe1;
		}

		public String Nom1;

		public String getNom1() {
			return this.Nom1;
		}

		public String Prenom1;

		public String getPrenom1() {
			return this.Prenom1;
		}

		public Integer Voix1;

		public Integer getVoix1() {
			return this.Voix1;
		}

		public BigDecimal PourcentageVoix_Inscrit1;

		public BigDecimal getPourcentageVoix_Inscrit1() {
			return this.PourcentageVoix_Inscrit1;
		}

		public BigDecimal PourcentageVoix_Exprime1;

		public BigDecimal getPourcentageVoix_Exprime1() {
			return this.PourcentageVoix_Exprime1;
		}

		public Integer NumeroPanneau2;

		public Integer getNumeroPanneau2() {
			return this.NumeroPanneau2;
		}

		public String Sexe2;

		public String getSexe2() {
			return this.Sexe2;
		}

		public String Nom2;

		public String getNom2() {
			return this.Nom2;
		}

		public String Prenom2;

		public String getPrenom2() {
			return this.Prenom2;
		}

		public Integer Voix2;

		public Integer getVoix2() {
			return this.Voix2;
		}

		public BigDecimal PourcentageVoix_Inscrit2;

		public BigDecimal getPourcentageVoix_Inscrit2() {
			return this.PourcentageVoix_Inscrit2;
		}

		public BigDecimal PourcentageVoix_Exprime2;

		public BigDecimal getPourcentageVoix_Exprime2() {
			return this.PourcentageVoix_Exprime2;
		}

		public Integer NumeroPanneau3;

		public Integer getNumeroPanneau3() {
			return this.NumeroPanneau3;
		}

		public String Sexe3;

		public String getSexe3() {
			return this.Sexe3;
		}

		public String Nom3;

		public String getNom3() {
			return this.Nom3;
		}

		public String Prenom3;

		public String getPrenom3() {
			return this.Prenom3;
		}

		public Integer Voix3;

		public Integer getVoix3() {
			return this.Voix3;
		}

		public BigDecimal PourcentageVoix_Inscrit3;

		public BigDecimal getPourcentageVoix_Inscrit3() {
			return this.PourcentageVoix_Inscrit3;
		}

		public BigDecimal PourcentageVoix_Exprime3;

		public BigDecimal getPourcentageVoix_Exprime3() {
			return this.PourcentageVoix_Exprime3;
		}

		public Integer NumeroPanneau4;

		public Integer getNumeroPanneau4() {
			return this.NumeroPanneau4;
		}

		public String Sexe4;

		public String getSexe4() {
			return this.Sexe4;
		}

		public String Nom4;

		public String getNom4() {
			return this.Nom4;
		}

		public String Prenom4;

		public String getPrenom4() {
			return this.Prenom4;
		}

		public Integer Voix4;

		public Integer getVoix4() {
			return this.Voix4;
		}

		public BigDecimal PourcentageVoix_Inscrit4;

		public BigDecimal getPourcentageVoix_Inscrit4() {
			return this.PourcentageVoix_Inscrit4;
		}

		public BigDecimal PourcentageVoix_Exprime4;

		public BigDecimal getPourcentageVoix_Exprime4() {
			return this.PourcentageVoix_Exprime4;
		}

		public Integer NumeroPanneau5;

		public Integer getNumeroPanneau5() {
			return this.NumeroPanneau5;
		}

		public String Sexe5;

		public String getSexe5() {
			return this.Sexe5;
		}

		public String Nom5;

		public String getNom5() {
			return this.Nom5;
		}

		public String Prenom5;

		public String getPrenom5() {
			return this.Prenom5;
		}

		public Integer Voix5;

		public Integer getVoix5() {
			return this.Voix5;
		}

		public BigDecimal PourcentageVoix_Inscrit5;

		public BigDecimal getPourcentageVoix_Inscrit5() {
			return this.PourcentageVoix_Inscrit5;
		}

		public BigDecimal PourcentageVoix_Exprime5;

		public BigDecimal getPourcentageVoix_Exprime5() {
			return this.PourcentageVoix_Exprime5;
		}

		public Integer NumeroPanneau6;

		public Integer getNumeroPanneau6() {
			return this.NumeroPanneau6;
		}

		public String Sexe6;

		public String getSexe6() {
			return this.Sexe6;
		}

		public String Nom6;

		public String getNom6() {
			return this.Nom6;
		}

		public String Prenom6;

		public String getPrenom6() {
			return this.Prenom6;
		}

		public Integer Voix6;

		public Integer getVoix6() {
			return this.Voix6;
		}

		public BigDecimal PourcentageVoix_Inscrit6;

		public BigDecimal getPourcentageVoix_Inscrit6() {
			return this.PourcentageVoix_Inscrit6;
		}

		public BigDecimal PourcentageVoix_Exprime6;

		public BigDecimal getPourcentageVoix_Exprime6() {
			return this.PourcentageVoix_Exprime6;
		}

		public Integer NumeroPanneau7;

		public Integer getNumeroPanneau7() {
			return this.NumeroPanneau7;
		}

		public String Sexe7;

		public String getSexe7() {
			return this.Sexe7;
		}

		public String Nom7;

		public String getNom7() {
			return this.Nom7;
		}

		public String Prenom7;

		public String getPrenom7() {
			return this.Prenom7;
		}

		public Integer Voix7;

		public Integer getVoix7() {
			return this.Voix7;
		}

		public BigDecimal PourcentageVoix_Inscrit7;

		public BigDecimal getPourcentageVoix_Inscrit7() {
			return this.PourcentageVoix_Inscrit7;
		}

		public BigDecimal PourcentageVoix_Exprime7;

		public BigDecimal getPourcentageVoix_Exprime7() {
			return this.PourcentageVoix_Exprime7;
		}

		public Integer NumeroPanneau8;

		public Integer getNumeroPanneau8() {
			return this.NumeroPanneau8;
		}

		public String Sexe8;

		public String getSexe8() {
			return this.Sexe8;
		}

		public String Nom8;

		public String getNom8() {
			return this.Nom8;
		}

		public String Prenom8;

		public String getPrenom8() {
			return this.Prenom8;
		}

		public Integer Voix8;

		public Integer getVoix8() {
			return this.Voix8;
		}

		public BigDecimal PourcentageVoix_Inscrit8;

		public BigDecimal getPourcentageVoix_Inscrit8() {
			return this.PourcentageVoix_Inscrit8;
		}

		public BigDecimal PourcentageVoix_Exprime8;

		public BigDecimal getPourcentageVoix_Exprime8() {
			return this.PourcentageVoix_Exprime8;
		}

		public Integer NumeroPanneau9;

		public Integer getNumeroPanneau9() {
			return this.NumeroPanneau9;
		}

		public String Sexe9;

		public String getSexe9() {
			return this.Sexe9;
		}

		public String Nom9;

		public String getNom9() {
			return this.Nom9;
		}

		public String Prenom9;

		public String getPrenom9() {
			return this.Prenom9;
		}

		public Integer Voix9;

		public Integer getVoix9() {
			return this.Voix9;
		}

		public BigDecimal PourcentageVoix_Inscrit9;

		public BigDecimal getPourcentageVoix_Inscrit9() {
			return this.PourcentageVoix_Inscrit9;
		}

		public BigDecimal PourcentageVoix_Exprime9;

		public BigDecimal getPourcentageVoix_Exprime9() {
			return this.PourcentageVoix_Exprime9;
		}

		public Integer NumeroPanneau10;

		public Integer getNumeroPanneau10() {
			return this.NumeroPanneau10;
		}

		public String Sexe10;

		public String getSexe10() {
			return this.Sexe10;
		}

		public String Nom10;

		public String getNom10() {
			return this.Nom10;
		}

		public String Prenom10;

		public String getPrenom10() {
			return this.Prenom10;
		}

		public Integer Voix10;

		public Integer getVoix10() {
			return this.Voix10;
		}

		public BigDecimal PourcentageVoix_Inscrit10;

		public BigDecimal getPourcentageVoix_Inscrit10() {
			return this.PourcentageVoix_Inscrit10;
		}

		public BigDecimal PourcentageVoix_Exprime10;

		public BigDecimal getPourcentageVoix_Exprime10() {
			return this.PourcentageVoix_Exprime10;
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
			other.CodeDepartement = this.CodeDepartement;
			other.LibelleDepartement = this.LibelleDepartement;
			other.CodeCirconscription = this.CodeCirconscription;
			other.LibelleCirconscription = this.LibelleCirconscription;
			other.Inscrit = this.Inscrit;
			other.Abstention = this.Abstention;
			other.PourcentageAbstention_Inscrit = this.PourcentageAbstention_Inscrit;
			other.Votant = this.Votant;
			other.PourcentageVotant_Inscrit = this.PourcentageVotant_Inscrit;
			other.VoteBlanc = this.VoteBlanc;
			other.PourcentageBlanc_Inscrit = this.PourcentageBlanc_Inscrit;
			other.PourcentageBlanc_Votant = this.PourcentageBlanc_Votant;
			other.VoteNul = this.VoteNul;
			other.PourcentageNul_Inscrit = this.PourcentageNul_Inscrit;
			other.PourcentageNul_Votant = this.PourcentageNul_Votant;
			other.Exprime = this.Exprime;
			other.PourcentageExprime_Inscrit = this.PourcentageExprime_Inscrit;
			other.PourcentageExprime_Votant = this.PourcentageExprime_Votant;
			other.NumeroPanneau = this.NumeroPanneau;
			other.Sexe = this.Sexe;
			other.Nom = this.Nom;
			other.Prenom = this.Prenom;
			other.Voix = this.Voix;
			other.PourcentageVoix_Inscrit = this.PourcentageVoix_Inscrit;
			other.PourcentageVoix_Exprime = this.PourcentageVoix_Exprime;
			other.NumeroPanneau1 = this.NumeroPanneau1;
			other.Sexe1 = this.Sexe1;
			other.Nom1 = this.Nom1;
			other.Prenom1 = this.Prenom1;
			other.Voix1 = this.Voix1;
			other.PourcentageVoix_Inscrit1 = this.PourcentageVoix_Inscrit1;
			other.PourcentageVoix_Exprime1 = this.PourcentageVoix_Exprime1;
			other.NumeroPanneau2 = this.NumeroPanneau2;
			other.Sexe2 = this.Sexe2;
			other.Nom2 = this.Nom2;
			other.Prenom2 = this.Prenom2;
			other.Voix2 = this.Voix2;
			other.PourcentageVoix_Inscrit2 = this.PourcentageVoix_Inscrit2;
			other.PourcentageVoix_Exprime2 = this.PourcentageVoix_Exprime2;
			other.NumeroPanneau3 = this.NumeroPanneau3;
			other.Sexe3 = this.Sexe3;
			other.Nom3 = this.Nom3;
			other.Prenom3 = this.Prenom3;
			other.Voix3 = this.Voix3;
			other.PourcentageVoix_Inscrit3 = this.PourcentageVoix_Inscrit3;
			other.PourcentageVoix_Exprime3 = this.PourcentageVoix_Exprime3;
			other.NumeroPanneau4 = this.NumeroPanneau4;
			other.Sexe4 = this.Sexe4;
			other.Nom4 = this.Nom4;
			other.Prenom4 = this.Prenom4;
			other.Voix4 = this.Voix4;
			other.PourcentageVoix_Inscrit4 = this.PourcentageVoix_Inscrit4;
			other.PourcentageVoix_Exprime4 = this.PourcentageVoix_Exprime4;
			other.NumeroPanneau5 = this.NumeroPanneau5;
			other.Sexe5 = this.Sexe5;
			other.Nom5 = this.Nom5;
			other.Prenom5 = this.Prenom5;
			other.Voix5 = this.Voix5;
			other.PourcentageVoix_Inscrit5 = this.PourcentageVoix_Inscrit5;
			other.PourcentageVoix_Exprime5 = this.PourcentageVoix_Exprime5;
			other.NumeroPanneau6 = this.NumeroPanneau6;
			other.Sexe6 = this.Sexe6;
			other.Nom6 = this.Nom6;
			other.Prenom6 = this.Prenom6;
			other.Voix6 = this.Voix6;
			other.PourcentageVoix_Inscrit6 = this.PourcentageVoix_Inscrit6;
			other.PourcentageVoix_Exprime6 = this.PourcentageVoix_Exprime6;
			other.NumeroPanneau7 = this.NumeroPanneau7;
			other.Sexe7 = this.Sexe7;
			other.Nom7 = this.Nom7;
			other.Prenom7 = this.Prenom7;
			other.Voix7 = this.Voix7;
			other.PourcentageVoix_Inscrit7 = this.PourcentageVoix_Inscrit7;
			other.PourcentageVoix_Exprime7 = this.PourcentageVoix_Exprime7;
			other.NumeroPanneau8 = this.NumeroPanneau8;
			other.Sexe8 = this.Sexe8;
			other.Nom8 = this.Nom8;
			other.Prenom8 = this.Prenom8;
			other.Voix8 = this.Voix8;
			other.PourcentageVoix_Inscrit8 = this.PourcentageVoix_Inscrit8;
			other.PourcentageVoix_Exprime8 = this.PourcentageVoix_Exprime8;
			other.NumeroPanneau9 = this.NumeroPanneau9;
			other.Sexe9 = this.Sexe9;
			other.Nom9 = this.Nom9;
			other.Prenom9 = this.Prenom9;
			other.Voix9 = this.Voix9;
			other.PourcentageVoix_Inscrit9 = this.PourcentageVoix_Inscrit9;
			other.PourcentageVoix_Exprime9 = this.PourcentageVoix_Exprime9;
			other.NumeroPanneau10 = this.NumeroPanneau10;
			other.Sexe10 = this.Sexe10;
			other.Nom10 = this.Nom10;
			other.Prenom10 = this.Prenom10;
			other.Voix10 = this.Voix10;
			other.PourcentageVoix_Inscrit10 = this.PourcentageVoix_Inscrit10;
			other.PourcentageVoix_Exprime10 = this.PourcentageVoix_Exprime10;

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
				if (length > commonByteArray_DATAMSPR_VoteElection2017.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_VoteElection2017.length == 0) {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_VoteElection2017, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_VoteElection2017, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_VoteElection2017.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_VoteElection2017.length == 0) {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_VoteElection2017, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_VoteElection2017, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_VoteElection2017) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.CodeDepartement = readString(dis);

					this.LibelleDepartement = readString(dis);

					this.CodeCirconscription = readString(dis);

					this.LibelleCirconscription = readString(dis);

					this.Inscrit = readInteger(dis);

					this.Abstention = readInteger(dis);

					this.PourcentageAbstention_Inscrit = (BigDecimal) dis.readObject();

					this.Votant = readInteger(dis);

					this.PourcentageVotant_Inscrit = (BigDecimal) dis.readObject();

					this.VoteBlanc = readInteger(dis);

					this.PourcentageBlanc_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageBlanc_Votant = (BigDecimal) dis.readObject();

					this.VoteNul = readInteger(dis);

					this.PourcentageNul_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageNul_Votant = (BigDecimal) dis.readObject();

					this.Exprime = readInteger(dis);

					this.PourcentageExprime_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageExprime_Votant = (BigDecimal) dis.readObject();

					this.NumeroPanneau = readInteger(dis);

					this.Sexe = readString(dis);

					this.Nom = readString(dis);

					this.Prenom = readString(dis);

					this.Voix = readInteger(dis);

					this.PourcentageVoix_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime = (BigDecimal) dis.readObject();

					this.NumeroPanneau1 = readInteger(dis);

					this.Sexe1 = readString(dis);

					this.Nom1 = readString(dis);

					this.Prenom1 = readString(dis);

					this.Voix1 = readInteger(dis);

					this.PourcentageVoix_Inscrit1 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime1 = (BigDecimal) dis.readObject();

					this.NumeroPanneau2 = readInteger(dis);

					this.Sexe2 = readString(dis);

					this.Nom2 = readString(dis);

					this.Prenom2 = readString(dis);

					this.Voix2 = readInteger(dis);

					this.PourcentageVoix_Inscrit2 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime2 = (BigDecimal) dis.readObject();

					this.NumeroPanneau3 = readInteger(dis);

					this.Sexe3 = readString(dis);

					this.Nom3 = readString(dis);

					this.Prenom3 = readString(dis);

					this.Voix3 = readInteger(dis);

					this.PourcentageVoix_Inscrit3 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime3 = (BigDecimal) dis.readObject();

					this.NumeroPanneau4 = readInteger(dis);

					this.Sexe4 = readString(dis);

					this.Nom4 = readString(dis);

					this.Prenom4 = readString(dis);

					this.Voix4 = readInteger(dis);

					this.PourcentageVoix_Inscrit4 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime4 = (BigDecimal) dis.readObject();

					this.NumeroPanneau5 = readInteger(dis);

					this.Sexe5 = readString(dis);

					this.Nom5 = readString(dis);

					this.Prenom5 = readString(dis);

					this.Voix5 = readInteger(dis);

					this.PourcentageVoix_Inscrit5 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime5 = (BigDecimal) dis.readObject();

					this.NumeroPanneau6 = readInteger(dis);

					this.Sexe6 = readString(dis);

					this.Nom6 = readString(dis);

					this.Prenom6 = readString(dis);

					this.Voix6 = readInteger(dis);

					this.PourcentageVoix_Inscrit6 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime6 = (BigDecimal) dis.readObject();

					this.NumeroPanneau7 = readInteger(dis);

					this.Sexe7 = readString(dis);

					this.Nom7 = readString(dis);

					this.Prenom7 = readString(dis);

					this.Voix7 = readInteger(dis);

					this.PourcentageVoix_Inscrit7 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime7 = (BigDecimal) dis.readObject();

					this.NumeroPanneau8 = readInteger(dis);

					this.Sexe8 = readString(dis);

					this.Nom8 = readString(dis);

					this.Prenom8 = readString(dis);

					this.Voix8 = readInteger(dis);

					this.PourcentageVoix_Inscrit8 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime8 = (BigDecimal) dis.readObject();

					this.NumeroPanneau9 = readInteger(dis);

					this.Sexe9 = readString(dis);

					this.Nom9 = readString(dis);

					this.Prenom9 = readString(dis);

					this.Voix9 = readInteger(dis);

					this.PourcentageVoix_Inscrit9 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime9 = (BigDecimal) dis.readObject();

					this.NumeroPanneau10 = readInteger(dis);

					this.Sexe10 = readString(dis);

					this.Nom10 = readString(dis);

					this.Prenom10 = readString(dis);

					this.Voix10 = readInteger(dis);

					this.PourcentageVoix_Inscrit10 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime10 = (BigDecimal) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_VoteElection2017) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.CodeDepartement = readString(dis);

					this.LibelleDepartement = readString(dis);

					this.CodeCirconscription = readString(dis);

					this.LibelleCirconscription = readString(dis);

					this.Inscrit = readInteger(dis);

					this.Abstention = readInteger(dis);

					this.PourcentageAbstention_Inscrit = (BigDecimal) dis.readObject();

					this.Votant = readInteger(dis);

					this.PourcentageVotant_Inscrit = (BigDecimal) dis.readObject();

					this.VoteBlanc = readInteger(dis);

					this.PourcentageBlanc_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageBlanc_Votant = (BigDecimal) dis.readObject();

					this.VoteNul = readInteger(dis);

					this.PourcentageNul_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageNul_Votant = (BigDecimal) dis.readObject();

					this.Exprime = readInteger(dis);

					this.PourcentageExprime_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageExprime_Votant = (BigDecimal) dis.readObject();

					this.NumeroPanneau = readInteger(dis);

					this.Sexe = readString(dis);

					this.Nom = readString(dis);

					this.Prenom = readString(dis);

					this.Voix = readInteger(dis);

					this.PourcentageVoix_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime = (BigDecimal) dis.readObject();

					this.NumeroPanneau1 = readInteger(dis);

					this.Sexe1 = readString(dis);

					this.Nom1 = readString(dis);

					this.Prenom1 = readString(dis);

					this.Voix1 = readInteger(dis);

					this.PourcentageVoix_Inscrit1 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime1 = (BigDecimal) dis.readObject();

					this.NumeroPanneau2 = readInteger(dis);

					this.Sexe2 = readString(dis);

					this.Nom2 = readString(dis);

					this.Prenom2 = readString(dis);

					this.Voix2 = readInteger(dis);

					this.PourcentageVoix_Inscrit2 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime2 = (BigDecimal) dis.readObject();

					this.NumeroPanneau3 = readInteger(dis);

					this.Sexe3 = readString(dis);

					this.Nom3 = readString(dis);

					this.Prenom3 = readString(dis);

					this.Voix3 = readInteger(dis);

					this.PourcentageVoix_Inscrit3 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime3 = (BigDecimal) dis.readObject();

					this.NumeroPanneau4 = readInteger(dis);

					this.Sexe4 = readString(dis);

					this.Nom4 = readString(dis);

					this.Prenom4 = readString(dis);

					this.Voix4 = readInteger(dis);

					this.PourcentageVoix_Inscrit4 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime4 = (BigDecimal) dis.readObject();

					this.NumeroPanneau5 = readInteger(dis);

					this.Sexe5 = readString(dis);

					this.Nom5 = readString(dis);

					this.Prenom5 = readString(dis);

					this.Voix5 = readInteger(dis);

					this.PourcentageVoix_Inscrit5 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime5 = (BigDecimal) dis.readObject();

					this.NumeroPanneau6 = readInteger(dis);

					this.Sexe6 = readString(dis);

					this.Nom6 = readString(dis);

					this.Prenom6 = readString(dis);

					this.Voix6 = readInteger(dis);

					this.PourcentageVoix_Inscrit6 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime6 = (BigDecimal) dis.readObject();

					this.NumeroPanneau7 = readInteger(dis);

					this.Sexe7 = readString(dis);

					this.Nom7 = readString(dis);

					this.Prenom7 = readString(dis);

					this.Voix7 = readInteger(dis);

					this.PourcentageVoix_Inscrit7 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime7 = (BigDecimal) dis.readObject();

					this.NumeroPanneau8 = readInteger(dis);

					this.Sexe8 = readString(dis);

					this.Nom8 = readString(dis);

					this.Prenom8 = readString(dis);

					this.Voix8 = readInteger(dis);

					this.PourcentageVoix_Inscrit8 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime8 = (BigDecimal) dis.readObject();

					this.NumeroPanneau9 = readInteger(dis);

					this.Sexe9 = readString(dis);

					this.Nom9 = readString(dis);

					this.Prenom9 = readString(dis);

					this.Voix9 = readInteger(dis);

					this.PourcentageVoix_Inscrit9 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime9 = (BigDecimal) dis.readObject();

					this.NumeroPanneau10 = readInteger(dis);

					this.Sexe10 = readString(dis);

					this.Nom10 = readString(dis);

					this.Prenom10 = readString(dis);

					this.Voix10 = readInteger(dis);

					this.PourcentageVoix_Inscrit10 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime10 = (BigDecimal) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.CodeDepartement, dos);

				// String

				writeString(this.LibelleDepartement, dos);

				// String

				writeString(this.CodeCirconscription, dos);

				// String

				writeString(this.LibelleCirconscription, dos);

				// Integer

				writeInteger(this.Inscrit, dos);

				// Integer

				writeInteger(this.Abstention, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageAbstention_Inscrit);

				// Integer

				writeInteger(this.Votant, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVotant_Inscrit);

				// Integer

				writeInteger(this.VoteBlanc, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Votant);

				// Integer

				writeInteger(this.VoteNul, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Votant);

				// Integer

				writeInteger(this.Exprime, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Votant);

				// Integer

				writeInteger(this.NumeroPanneau, dos);

				// String

				writeString(this.Sexe, dos);

				// String

				writeString(this.Nom, dos);

				// String

				writeString(this.Prenom, dos);

				// Integer

				writeInteger(this.Voix, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime);

				// Integer

				writeInteger(this.NumeroPanneau1, dos);

				// String

				writeString(this.Sexe1, dos);

				// String

				writeString(this.Nom1, dos);

				// String

				writeString(this.Prenom1, dos);

				// Integer

				writeInteger(this.Voix1, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit1);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime1);

				// Integer

				writeInteger(this.NumeroPanneau2, dos);

				// String

				writeString(this.Sexe2, dos);

				// String

				writeString(this.Nom2, dos);

				// String

				writeString(this.Prenom2, dos);

				// Integer

				writeInteger(this.Voix2, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit2);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime2);

				// Integer

				writeInteger(this.NumeroPanneau3, dos);

				// String

				writeString(this.Sexe3, dos);

				// String

				writeString(this.Nom3, dos);

				// String

				writeString(this.Prenom3, dos);

				// Integer

				writeInteger(this.Voix3, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit3);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime3);

				// Integer

				writeInteger(this.NumeroPanneau4, dos);

				// String

				writeString(this.Sexe4, dos);

				// String

				writeString(this.Nom4, dos);

				// String

				writeString(this.Prenom4, dos);

				// Integer

				writeInteger(this.Voix4, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit4);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime4);

				// Integer

				writeInteger(this.NumeroPanneau5, dos);

				// String

				writeString(this.Sexe5, dos);

				// String

				writeString(this.Nom5, dos);

				// String

				writeString(this.Prenom5, dos);

				// Integer

				writeInteger(this.Voix5, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit5);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime5);

				// Integer

				writeInteger(this.NumeroPanneau6, dos);

				// String

				writeString(this.Sexe6, dos);

				// String

				writeString(this.Nom6, dos);

				// String

				writeString(this.Prenom6, dos);

				// Integer

				writeInteger(this.Voix6, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit6);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime6);

				// Integer

				writeInteger(this.NumeroPanneau7, dos);

				// String

				writeString(this.Sexe7, dos);

				// String

				writeString(this.Nom7, dos);

				// String

				writeString(this.Prenom7, dos);

				// Integer

				writeInteger(this.Voix7, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit7);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime7);

				// Integer

				writeInteger(this.NumeroPanneau8, dos);

				// String

				writeString(this.Sexe8, dos);

				// String

				writeString(this.Nom8, dos);

				// String

				writeString(this.Prenom8, dos);

				// Integer

				writeInteger(this.Voix8, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit8);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime8);

				// Integer

				writeInteger(this.NumeroPanneau9, dos);

				// String

				writeString(this.Sexe9, dos);

				// String

				writeString(this.Nom9, dos);

				// String

				writeString(this.Prenom9, dos);

				// Integer

				writeInteger(this.Voix9, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit9);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime9);

				// Integer

				writeInteger(this.NumeroPanneau10, dos);

				// String

				writeString(this.Sexe10, dos);

				// String

				writeString(this.Nom10, dos);

				// String

				writeString(this.Prenom10, dos);

				// Integer

				writeInteger(this.Voix10, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit10);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime10);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.CodeDepartement, dos);

				// String

				writeString(this.LibelleDepartement, dos);

				// String

				writeString(this.CodeCirconscription, dos);

				// String

				writeString(this.LibelleCirconscription, dos);

				// Integer

				writeInteger(this.Inscrit, dos);

				// Integer

				writeInteger(this.Abstention, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageAbstention_Inscrit);

				// Integer

				writeInteger(this.Votant, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVotant_Inscrit);

				// Integer

				writeInteger(this.VoteBlanc, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Votant);

				// Integer

				writeInteger(this.VoteNul, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Votant);

				// Integer

				writeInteger(this.Exprime, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Votant);

				// Integer

				writeInteger(this.NumeroPanneau, dos);

				// String

				writeString(this.Sexe, dos);

				// String

				writeString(this.Nom, dos);

				// String

				writeString(this.Prenom, dos);

				// Integer

				writeInteger(this.Voix, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime);

				// Integer

				writeInteger(this.NumeroPanneau1, dos);

				// String

				writeString(this.Sexe1, dos);

				// String

				writeString(this.Nom1, dos);

				// String

				writeString(this.Prenom1, dos);

				// Integer

				writeInteger(this.Voix1, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit1);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime1);

				// Integer

				writeInteger(this.NumeroPanneau2, dos);

				// String

				writeString(this.Sexe2, dos);

				// String

				writeString(this.Nom2, dos);

				// String

				writeString(this.Prenom2, dos);

				// Integer

				writeInteger(this.Voix2, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit2);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime2);

				// Integer

				writeInteger(this.NumeroPanneau3, dos);

				// String

				writeString(this.Sexe3, dos);

				// String

				writeString(this.Nom3, dos);

				// String

				writeString(this.Prenom3, dos);

				// Integer

				writeInteger(this.Voix3, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit3);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime3);

				// Integer

				writeInteger(this.NumeroPanneau4, dos);

				// String

				writeString(this.Sexe4, dos);

				// String

				writeString(this.Nom4, dos);

				// String

				writeString(this.Prenom4, dos);

				// Integer

				writeInteger(this.Voix4, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit4);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime4);

				// Integer

				writeInteger(this.NumeroPanneau5, dos);

				// String

				writeString(this.Sexe5, dos);

				// String

				writeString(this.Nom5, dos);

				// String

				writeString(this.Prenom5, dos);

				// Integer

				writeInteger(this.Voix5, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit5);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime5);

				// Integer

				writeInteger(this.NumeroPanneau6, dos);

				// String

				writeString(this.Sexe6, dos);

				// String

				writeString(this.Nom6, dos);

				// String

				writeString(this.Prenom6, dos);

				// Integer

				writeInteger(this.Voix6, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit6);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime6);

				// Integer

				writeInteger(this.NumeroPanneau7, dos);

				// String

				writeString(this.Sexe7, dos);

				// String

				writeString(this.Nom7, dos);

				// String

				writeString(this.Prenom7, dos);

				// Integer

				writeInteger(this.Voix7, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit7);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime7);

				// Integer

				writeInteger(this.NumeroPanneau8, dos);

				// String

				writeString(this.Sexe8, dos);

				// String

				writeString(this.Nom8, dos);

				// String

				writeString(this.Prenom8, dos);

				// Integer

				writeInteger(this.Voix8, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit8);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime8);

				// Integer

				writeInteger(this.NumeroPanneau9, dos);

				// String

				writeString(this.Sexe9, dos);

				// String

				writeString(this.Nom9, dos);

				// String

				writeString(this.Prenom9, dos);

				// Integer

				writeInteger(this.Voix9, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit9);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime9);

				// Integer

				writeInteger(this.NumeroPanneau10, dos);

				// String

				writeString(this.Sexe10, dos);

				// String

				writeString(this.Nom10, dos);

				// String

				writeString(this.Prenom10, dos);

				// Integer

				writeInteger(this.Voix10, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit10);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime10);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",CodeDepartement=" + CodeDepartement);
			sb.append(",LibelleDepartement=" + LibelleDepartement);
			sb.append(",CodeCirconscription=" + CodeCirconscription);
			sb.append(",LibelleCirconscription=" + LibelleCirconscription);
			sb.append(",Inscrit=" + String.valueOf(Inscrit));
			sb.append(",Abstention=" + String.valueOf(Abstention));
			sb.append(",PourcentageAbstention_Inscrit=" + String.valueOf(PourcentageAbstention_Inscrit));
			sb.append(",Votant=" + String.valueOf(Votant));
			sb.append(",PourcentageVotant_Inscrit=" + String.valueOf(PourcentageVotant_Inscrit));
			sb.append(",VoteBlanc=" + String.valueOf(VoteBlanc));
			sb.append(",PourcentageBlanc_Inscrit=" + String.valueOf(PourcentageBlanc_Inscrit));
			sb.append(",PourcentageBlanc_Votant=" + String.valueOf(PourcentageBlanc_Votant));
			sb.append(",VoteNul=" + String.valueOf(VoteNul));
			sb.append(",PourcentageNul_Inscrit=" + String.valueOf(PourcentageNul_Inscrit));
			sb.append(",PourcentageNul_Votant=" + String.valueOf(PourcentageNul_Votant));
			sb.append(",Exprime=" + String.valueOf(Exprime));
			sb.append(",PourcentageExprime_Inscrit=" + String.valueOf(PourcentageExprime_Inscrit));
			sb.append(",PourcentageExprime_Votant=" + String.valueOf(PourcentageExprime_Votant));
			sb.append(",NumeroPanneau=" + String.valueOf(NumeroPanneau));
			sb.append(",Sexe=" + Sexe);
			sb.append(",Nom=" + Nom);
			sb.append(",Prenom=" + Prenom);
			sb.append(",Voix=" + String.valueOf(Voix));
			sb.append(",PourcentageVoix_Inscrit=" + String.valueOf(PourcentageVoix_Inscrit));
			sb.append(",PourcentageVoix_Exprime=" + String.valueOf(PourcentageVoix_Exprime));
			sb.append(",NumeroPanneau1=" + String.valueOf(NumeroPanneau1));
			sb.append(",Sexe1=" + Sexe1);
			sb.append(",Nom1=" + Nom1);
			sb.append(",Prenom1=" + Prenom1);
			sb.append(",Voix1=" + String.valueOf(Voix1));
			sb.append(",PourcentageVoix_Inscrit1=" + String.valueOf(PourcentageVoix_Inscrit1));
			sb.append(",PourcentageVoix_Exprime1=" + String.valueOf(PourcentageVoix_Exprime1));
			sb.append(",NumeroPanneau2=" + String.valueOf(NumeroPanneau2));
			sb.append(",Sexe2=" + Sexe2);
			sb.append(",Nom2=" + Nom2);
			sb.append(",Prenom2=" + Prenom2);
			sb.append(",Voix2=" + String.valueOf(Voix2));
			sb.append(",PourcentageVoix_Inscrit2=" + String.valueOf(PourcentageVoix_Inscrit2));
			sb.append(",PourcentageVoix_Exprime2=" + String.valueOf(PourcentageVoix_Exprime2));
			sb.append(",NumeroPanneau3=" + String.valueOf(NumeroPanneau3));
			sb.append(",Sexe3=" + Sexe3);
			sb.append(",Nom3=" + Nom3);
			sb.append(",Prenom3=" + Prenom3);
			sb.append(",Voix3=" + String.valueOf(Voix3));
			sb.append(",PourcentageVoix_Inscrit3=" + String.valueOf(PourcentageVoix_Inscrit3));
			sb.append(",PourcentageVoix_Exprime3=" + String.valueOf(PourcentageVoix_Exprime3));
			sb.append(",NumeroPanneau4=" + String.valueOf(NumeroPanneau4));
			sb.append(",Sexe4=" + Sexe4);
			sb.append(",Nom4=" + Nom4);
			sb.append(",Prenom4=" + Prenom4);
			sb.append(",Voix4=" + String.valueOf(Voix4));
			sb.append(",PourcentageVoix_Inscrit4=" + String.valueOf(PourcentageVoix_Inscrit4));
			sb.append(",PourcentageVoix_Exprime4=" + String.valueOf(PourcentageVoix_Exprime4));
			sb.append(",NumeroPanneau5=" + String.valueOf(NumeroPanneau5));
			sb.append(",Sexe5=" + Sexe5);
			sb.append(",Nom5=" + Nom5);
			sb.append(",Prenom5=" + Prenom5);
			sb.append(",Voix5=" + String.valueOf(Voix5));
			sb.append(",PourcentageVoix_Inscrit5=" + String.valueOf(PourcentageVoix_Inscrit5));
			sb.append(",PourcentageVoix_Exprime5=" + String.valueOf(PourcentageVoix_Exprime5));
			sb.append(",NumeroPanneau6=" + String.valueOf(NumeroPanneau6));
			sb.append(",Sexe6=" + Sexe6);
			sb.append(",Nom6=" + Nom6);
			sb.append(",Prenom6=" + Prenom6);
			sb.append(",Voix6=" + String.valueOf(Voix6));
			sb.append(",PourcentageVoix_Inscrit6=" + String.valueOf(PourcentageVoix_Inscrit6));
			sb.append(",PourcentageVoix_Exprime6=" + String.valueOf(PourcentageVoix_Exprime6));
			sb.append(",NumeroPanneau7=" + String.valueOf(NumeroPanneau7));
			sb.append(",Sexe7=" + Sexe7);
			sb.append(",Nom7=" + Nom7);
			sb.append(",Prenom7=" + Prenom7);
			sb.append(",Voix7=" + String.valueOf(Voix7));
			sb.append(",PourcentageVoix_Inscrit7=" + String.valueOf(PourcentageVoix_Inscrit7));
			sb.append(",PourcentageVoix_Exprime7=" + String.valueOf(PourcentageVoix_Exprime7));
			sb.append(",NumeroPanneau8=" + String.valueOf(NumeroPanneau8));
			sb.append(",Sexe8=" + Sexe8);
			sb.append(",Nom8=" + Nom8);
			sb.append(",Prenom8=" + Prenom8);
			sb.append(",Voix8=" + String.valueOf(Voix8));
			sb.append(",PourcentageVoix_Inscrit8=" + String.valueOf(PourcentageVoix_Inscrit8));
			sb.append(",PourcentageVoix_Exprime8=" + String.valueOf(PourcentageVoix_Exprime8));
			sb.append(",NumeroPanneau9=" + String.valueOf(NumeroPanneau9));
			sb.append(",Sexe9=" + Sexe9);
			sb.append(",Nom9=" + Nom9);
			sb.append(",Prenom9=" + Prenom9);
			sb.append(",Voix9=" + String.valueOf(Voix9));
			sb.append(",PourcentageVoix_Inscrit9=" + String.valueOf(PourcentageVoix_Inscrit9));
			sb.append(",PourcentageVoix_Exprime9=" + String.valueOf(PourcentageVoix_Exprime9));
			sb.append(",NumeroPanneau10=" + String.valueOf(NumeroPanneau10));
			sb.append(",Sexe10=" + Sexe10);
			sb.append(",Nom10=" + Nom10);
			sb.append(",Prenom10=" + Prenom10);
			sb.append(",Voix10=" + String.valueOf(Voix10));
			sb.append(",PourcentageVoix_Inscrit10=" + String.valueOf(PourcentageVoix_Inscrit10));
			sb.append(",PourcentageVoix_Exprime10=" + String.valueOf(PourcentageVoix_Exprime10));
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
		final static byte[] commonByteArrayLock_DATAMSPR_VoteElection2017 = new byte[0];
		static byte[] commonByteArray_DATAMSPR_VoteElection2017 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Id;

		public int getId() {
			return this.Id;
		}

		public String CodeDepartement;

		public String getCodeDepartement() {
			return this.CodeDepartement;
		}

		public String LibelleDepartement;

		public String getLibelleDepartement() {
			return this.LibelleDepartement;
		}

		public String CodeCirconscription;

		public String getCodeCirconscription() {
			return this.CodeCirconscription;
		}

		public String LibelleCirconscription;

		public String getLibelleCirconscription() {
			return this.LibelleCirconscription;
		}

		public Integer Inscrit;

		public Integer getInscrit() {
			return this.Inscrit;
		}

		public Integer Abstention;

		public Integer getAbstention() {
			return this.Abstention;
		}

		public BigDecimal PourcentageAbstention_Inscrit;

		public BigDecimal getPourcentageAbstention_Inscrit() {
			return this.PourcentageAbstention_Inscrit;
		}

		public Integer Votant;

		public Integer getVotant() {
			return this.Votant;
		}

		public BigDecimal PourcentageVotant_Inscrit;

		public BigDecimal getPourcentageVotant_Inscrit() {
			return this.PourcentageVotant_Inscrit;
		}

		public Integer VoteBlanc;

		public Integer getVoteBlanc() {
			return this.VoteBlanc;
		}

		public BigDecimal PourcentageBlanc_Inscrit;

		public BigDecimal getPourcentageBlanc_Inscrit() {
			return this.PourcentageBlanc_Inscrit;
		}

		public BigDecimal PourcentageBlanc_Votant;

		public BigDecimal getPourcentageBlanc_Votant() {
			return this.PourcentageBlanc_Votant;
		}

		public Integer VoteNul;

		public Integer getVoteNul() {
			return this.VoteNul;
		}

		public BigDecimal PourcentageNul_Inscrit;

		public BigDecimal getPourcentageNul_Inscrit() {
			return this.PourcentageNul_Inscrit;
		}

		public BigDecimal PourcentageNul_Votant;

		public BigDecimal getPourcentageNul_Votant() {
			return this.PourcentageNul_Votant;
		}

		public Integer Exprime;

		public Integer getExprime() {
			return this.Exprime;
		}

		public BigDecimal PourcentageExprime_Inscrit;

		public BigDecimal getPourcentageExprime_Inscrit() {
			return this.PourcentageExprime_Inscrit;
		}

		public BigDecimal PourcentageExprime_Votant;

		public BigDecimal getPourcentageExprime_Votant() {
			return this.PourcentageExprime_Votant;
		}

		public Integer NumeroPanneau;

		public Integer getNumeroPanneau() {
			return this.NumeroPanneau;
		}

		public String Sexe;

		public String getSexe() {
			return this.Sexe;
		}

		public String Nom;

		public String getNom() {
			return this.Nom;
		}

		public String Prenom;

		public String getPrenom() {
			return this.Prenom;
		}

		public Integer Voix;

		public Integer getVoix() {
			return this.Voix;
		}

		public BigDecimal PourcentageVoix_Inscrit;

		public BigDecimal getPourcentageVoix_Inscrit() {
			return this.PourcentageVoix_Inscrit;
		}

		public BigDecimal PourcentageVoix_Exprime;

		public BigDecimal getPourcentageVoix_Exprime() {
			return this.PourcentageVoix_Exprime;
		}

		public Integer NumeroPanneau1;

		public Integer getNumeroPanneau1() {
			return this.NumeroPanneau1;
		}

		public String Sexe1;

		public String getSexe1() {
			return this.Sexe1;
		}

		public String Nom1;

		public String getNom1() {
			return this.Nom1;
		}

		public String Prenom1;

		public String getPrenom1() {
			return this.Prenom1;
		}

		public Integer Voix1;

		public Integer getVoix1() {
			return this.Voix1;
		}

		public BigDecimal PourcentageVoix_Inscrit1;

		public BigDecimal getPourcentageVoix_Inscrit1() {
			return this.PourcentageVoix_Inscrit1;
		}

		public BigDecimal PourcentageVoix_Exprime1;

		public BigDecimal getPourcentageVoix_Exprime1() {
			return this.PourcentageVoix_Exprime1;
		}

		public Integer NumeroPanneau2;

		public Integer getNumeroPanneau2() {
			return this.NumeroPanneau2;
		}

		public String Sexe2;

		public String getSexe2() {
			return this.Sexe2;
		}

		public String Nom2;

		public String getNom2() {
			return this.Nom2;
		}

		public String Prenom2;

		public String getPrenom2() {
			return this.Prenom2;
		}

		public Integer Voix2;

		public Integer getVoix2() {
			return this.Voix2;
		}

		public BigDecimal PourcentageVoix_Inscrit2;

		public BigDecimal getPourcentageVoix_Inscrit2() {
			return this.PourcentageVoix_Inscrit2;
		}

		public BigDecimal PourcentageVoix_Exprime2;

		public BigDecimal getPourcentageVoix_Exprime2() {
			return this.PourcentageVoix_Exprime2;
		}

		public Integer NumeroPanneau3;

		public Integer getNumeroPanneau3() {
			return this.NumeroPanneau3;
		}

		public String Sexe3;

		public String getSexe3() {
			return this.Sexe3;
		}

		public String Nom3;

		public String getNom3() {
			return this.Nom3;
		}

		public String Prenom3;

		public String getPrenom3() {
			return this.Prenom3;
		}

		public Integer Voix3;

		public Integer getVoix3() {
			return this.Voix3;
		}

		public BigDecimal PourcentageVoix_Inscrit3;

		public BigDecimal getPourcentageVoix_Inscrit3() {
			return this.PourcentageVoix_Inscrit3;
		}

		public BigDecimal PourcentageVoix_Exprime3;

		public BigDecimal getPourcentageVoix_Exprime3() {
			return this.PourcentageVoix_Exprime3;
		}

		public Integer NumeroPanneau4;

		public Integer getNumeroPanneau4() {
			return this.NumeroPanneau4;
		}

		public String Sexe4;

		public String getSexe4() {
			return this.Sexe4;
		}

		public String Nom4;

		public String getNom4() {
			return this.Nom4;
		}

		public String Prenom4;

		public String getPrenom4() {
			return this.Prenom4;
		}

		public Integer Voix4;

		public Integer getVoix4() {
			return this.Voix4;
		}

		public BigDecimal PourcentageVoix_Inscrit4;

		public BigDecimal getPourcentageVoix_Inscrit4() {
			return this.PourcentageVoix_Inscrit4;
		}

		public BigDecimal PourcentageVoix_Exprime4;

		public BigDecimal getPourcentageVoix_Exprime4() {
			return this.PourcentageVoix_Exprime4;
		}

		public Integer NumeroPanneau5;

		public Integer getNumeroPanneau5() {
			return this.NumeroPanneau5;
		}

		public String Sexe5;

		public String getSexe5() {
			return this.Sexe5;
		}

		public String Nom5;

		public String getNom5() {
			return this.Nom5;
		}

		public String Prenom5;

		public String getPrenom5() {
			return this.Prenom5;
		}

		public Integer Voix5;

		public Integer getVoix5() {
			return this.Voix5;
		}

		public BigDecimal PourcentageVoix_Inscrit5;

		public BigDecimal getPourcentageVoix_Inscrit5() {
			return this.PourcentageVoix_Inscrit5;
		}

		public BigDecimal PourcentageVoix_Exprime5;

		public BigDecimal getPourcentageVoix_Exprime5() {
			return this.PourcentageVoix_Exprime5;
		}

		public Integer NumeroPanneau6;

		public Integer getNumeroPanneau6() {
			return this.NumeroPanneau6;
		}

		public String Sexe6;

		public String getSexe6() {
			return this.Sexe6;
		}

		public String Nom6;

		public String getNom6() {
			return this.Nom6;
		}

		public String Prenom6;

		public String getPrenom6() {
			return this.Prenom6;
		}

		public Integer Voix6;

		public Integer getVoix6() {
			return this.Voix6;
		}

		public BigDecimal PourcentageVoix_Inscrit6;

		public BigDecimal getPourcentageVoix_Inscrit6() {
			return this.PourcentageVoix_Inscrit6;
		}

		public BigDecimal PourcentageVoix_Exprime6;

		public BigDecimal getPourcentageVoix_Exprime6() {
			return this.PourcentageVoix_Exprime6;
		}

		public Integer NumeroPanneau7;

		public Integer getNumeroPanneau7() {
			return this.NumeroPanneau7;
		}

		public String Sexe7;

		public String getSexe7() {
			return this.Sexe7;
		}

		public String Nom7;

		public String getNom7() {
			return this.Nom7;
		}

		public String Prenom7;

		public String getPrenom7() {
			return this.Prenom7;
		}

		public Integer Voix7;

		public Integer getVoix7() {
			return this.Voix7;
		}

		public BigDecimal PourcentageVoix_Inscrit7;

		public BigDecimal getPourcentageVoix_Inscrit7() {
			return this.PourcentageVoix_Inscrit7;
		}

		public BigDecimal PourcentageVoix_Exprime7;

		public BigDecimal getPourcentageVoix_Exprime7() {
			return this.PourcentageVoix_Exprime7;
		}

		public Integer NumeroPanneau8;

		public Integer getNumeroPanneau8() {
			return this.NumeroPanneau8;
		}

		public String Sexe8;

		public String getSexe8() {
			return this.Sexe8;
		}

		public String Nom8;

		public String getNom8() {
			return this.Nom8;
		}

		public String Prenom8;

		public String getPrenom8() {
			return this.Prenom8;
		}

		public Integer Voix8;

		public Integer getVoix8() {
			return this.Voix8;
		}

		public BigDecimal PourcentageVoix_Inscrit8;

		public BigDecimal getPourcentageVoix_Inscrit8() {
			return this.PourcentageVoix_Inscrit8;
		}

		public BigDecimal PourcentageVoix_Exprime8;

		public BigDecimal getPourcentageVoix_Exprime8() {
			return this.PourcentageVoix_Exprime8;
		}

		public Integer NumeroPanneau9;

		public Integer getNumeroPanneau9() {
			return this.NumeroPanneau9;
		}

		public String Sexe9;

		public String getSexe9() {
			return this.Sexe9;
		}

		public String Nom9;

		public String getNom9() {
			return this.Nom9;
		}

		public String Prenom9;

		public String getPrenom9() {
			return this.Prenom9;
		}

		public Integer Voix9;

		public Integer getVoix9() {
			return this.Voix9;
		}

		public BigDecimal PourcentageVoix_Inscrit9;

		public BigDecimal getPourcentageVoix_Inscrit9() {
			return this.PourcentageVoix_Inscrit9;
		}

		public BigDecimal PourcentageVoix_Exprime9;

		public BigDecimal getPourcentageVoix_Exprime9() {
			return this.PourcentageVoix_Exprime9;
		}

		public Integer NumeroPanneau10;

		public Integer getNumeroPanneau10() {
			return this.NumeroPanneau10;
		}

		public String Sexe10;

		public String getSexe10() {
			return this.Sexe10;
		}

		public String Nom10;

		public String getNom10() {
			return this.Nom10;
		}

		public String Prenom10;

		public String getPrenom10() {
			return this.Prenom10;
		}

		public Integer Voix10;

		public Integer getVoix10() {
			return this.Voix10;
		}

		public BigDecimal PourcentageVoix_Inscrit10;

		public BigDecimal getPourcentageVoix_Inscrit10() {
			return this.PourcentageVoix_Inscrit10;
		}

		public BigDecimal PourcentageVoix_Exprime10;

		public BigDecimal getPourcentageVoix_Exprime10() {
			return this.PourcentageVoix_Exprime10;
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
			other.CodeDepartement = this.CodeDepartement;
			other.LibelleDepartement = this.LibelleDepartement;
			other.CodeCirconscription = this.CodeCirconscription;
			other.LibelleCirconscription = this.LibelleCirconscription;
			other.Inscrit = this.Inscrit;
			other.Abstention = this.Abstention;
			other.PourcentageAbstention_Inscrit = this.PourcentageAbstention_Inscrit;
			other.Votant = this.Votant;
			other.PourcentageVotant_Inscrit = this.PourcentageVotant_Inscrit;
			other.VoteBlanc = this.VoteBlanc;
			other.PourcentageBlanc_Inscrit = this.PourcentageBlanc_Inscrit;
			other.PourcentageBlanc_Votant = this.PourcentageBlanc_Votant;
			other.VoteNul = this.VoteNul;
			other.PourcentageNul_Inscrit = this.PourcentageNul_Inscrit;
			other.PourcentageNul_Votant = this.PourcentageNul_Votant;
			other.Exprime = this.Exprime;
			other.PourcentageExprime_Inscrit = this.PourcentageExprime_Inscrit;
			other.PourcentageExprime_Votant = this.PourcentageExprime_Votant;
			other.NumeroPanneau = this.NumeroPanneau;
			other.Sexe = this.Sexe;
			other.Nom = this.Nom;
			other.Prenom = this.Prenom;
			other.Voix = this.Voix;
			other.PourcentageVoix_Inscrit = this.PourcentageVoix_Inscrit;
			other.PourcentageVoix_Exprime = this.PourcentageVoix_Exprime;
			other.NumeroPanneau1 = this.NumeroPanneau1;
			other.Sexe1 = this.Sexe1;
			other.Nom1 = this.Nom1;
			other.Prenom1 = this.Prenom1;
			other.Voix1 = this.Voix1;
			other.PourcentageVoix_Inscrit1 = this.PourcentageVoix_Inscrit1;
			other.PourcentageVoix_Exprime1 = this.PourcentageVoix_Exprime1;
			other.NumeroPanneau2 = this.NumeroPanneau2;
			other.Sexe2 = this.Sexe2;
			other.Nom2 = this.Nom2;
			other.Prenom2 = this.Prenom2;
			other.Voix2 = this.Voix2;
			other.PourcentageVoix_Inscrit2 = this.PourcentageVoix_Inscrit2;
			other.PourcentageVoix_Exprime2 = this.PourcentageVoix_Exprime2;
			other.NumeroPanneau3 = this.NumeroPanneau3;
			other.Sexe3 = this.Sexe3;
			other.Nom3 = this.Nom3;
			other.Prenom3 = this.Prenom3;
			other.Voix3 = this.Voix3;
			other.PourcentageVoix_Inscrit3 = this.PourcentageVoix_Inscrit3;
			other.PourcentageVoix_Exprime3 = this.PourcentageVoix_Exprime3;
			other.NumeroPanneau4 = this.NumeroPanneau4;
			other.Sexe4 = this.Sexe4;
			other.Nom4 = this.Nom4;
			other.Prenom4 = this.Prenom4;
			other.Voix4 = this.Voix4;
			other.PourcentageVoix_Inscrit4 = this.PourcentageVoix_Inscrit4;
			other.PourcentageVoix_Exprime4 = this.PourcentageVoix_Exprime4;
			other.NumeroPanneau5 = this.NumeroPanneau5;
			other.Sexe5 = this.Sexe5;
			other.Nom5 = this.Nom5;
			other.Prenom5 = this.Prenom5;
			other.Voix5 = this.Voix5;
			other.PourcentageVoix_Inscrit5 = this.PourcentageVoix_Inscrit5;
			other.PourcentageVoix_Exprime5 = this.PourcentageVoix_Exprime5;
			other.NumeroPanneau6 = this.NumeroPanneau6;
			other.Sexe6 = this.Sexe6;
			other.Nom6 = this.Nom6;
			other.Prenom6 = this.Prenom6;
			other.Voix6 = this.Voix6;
			other.PourcentageVoix_Inscrit6 = this.PourcentageVoix_Inscrit6;
			other.PourcentageVoix_Exprime6 = this.PourcentageVoix_Exprime6;
			other.NumeroPanneau7 = this.NumeroPanneau7;
			other.Sexe7 = this.Sexe7;
			other.Nom7 = this.Nom7;
			other.Prenom7 = this.Prenom7;
			other.Voix7 = this.Voix7;
			other.PourcentageVoix_Inscrit7 = this.PourcentageVoix_Inscrit7;
			other.PourcentageVoix_Exprime7 = this.PourcentageVoix_Exprime7;
			other.NumeroPanneau8 = this.NumeroPanneau8;
			other.Sexe8 = this.Sexe8;
			other.Nom8 = this.Nom8;
			other.Prenom8 = this.Prenom8;
			other.Voix8 = this.Voix8;
			other.PourcentageVoix_Inscrit8 = this.PourcentageVoix_Inscrit8;
			other.PourcentageVoix_Exprime8 = this.PourcentageVoix_Exprime8;
			other.NumeroPanneau9 = this.NumeroPanneau9;
			other.Sexe9 = this.Sexe9;
			other.Nom9 = this.Nom9;
			other.Prenom9 = this.Prenom9;
			other.Voix9 = this.Voix9;
			other.PourcentageVoix_Inscrit9 = this.PourcentageVoix_Inscrit9;
			other.PourcentageVoix_Exprime9 = this.PourcentageVoix_Exprime9;
			other.NumeroPanneau10 = this.NumeroPanneau10;
			other.Sexe10 = this.Sexe10;
			other.Nom10 = this.Nom10;
			other.Prenom10 = this.Prenom10;
			other.Voix10 = this.Voix10;
			other.PourcentageVoix_Inscrit10 = this.PourcentageVoix_Inscrit10;
			other.PourcentageVoix_Exprime10 = this.PourcentageVoix_Exprime10;

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
				if (length > commonByteArray_DATAMSPR_VoteElection2017.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_VoteElection2017.length == 0) {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_VoteElection2017, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_VoteElection2017, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_VoteElection2017.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_VoteElection2017.length == 0) {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_VoteElection2017, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_VoteElection2017, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_VoteElection2017) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.CodeDepartement = readString(dis);

					this.LibelleDepartement = readString(dis);

					this.CodeCirconscription = readString(dis);

					this.LibelleCirconscription = readString(dis);

					this.Inscrit = readInteger(dis);

					this.Abstention = readInteger(dis);

					this.PourcentageAbstention_Inscrit = (BigDecimal) dis.readObject();

					this.Votant = readInteger(dis);

					this.PourcentageVotant_Inscrit = (BigDecimal) dis.readObject();

					this.VoteBlanc = readInteger(dis);

					this.PourcentageBlanc_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageBlanc_Votant = (BigDecimal) dis.readObject();

					this.VoteNul = readInteger(dis);

					this.PourcentageNul_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageNul_Votant = (BigDecimal) dis.readObject();

					this.Exprime = readInteger(dis);

					this.PourcentageExprime_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageExprime_Votant = (BigDecimal) dis.readObject();

					this.NumeroPanneau = readInteger(dis);

					this.Sexe = readString(dis);

					this.Nom = readString(dis);

					this.Prenom = readString(dis);

					this.Voix = readInteger(dis);

					this.PourcentageVoix_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime = (BigDecimal) dis.readObject();

					this.NumeroPanneau1 = readInteger(dis);

					this.Sexe1 = readString(dis);

					this.Nom1 = readString(dis);

					this.Prenom1 = readString(dis);

					this.Voix1 = readInteger(dis);

					this.PourcentageVoix_Inscrit1 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime1 = (BigDecimal) dis.readObject();

					this.NumeroPanneau2 = readInteger(dis);

					this.Sexe2 = readString(dis);

					this.Nom2 = readString(dis);

					this.Prenom2 = readString(dis);

					this.Voix2 = readInteger(dis);

					this.PourcentageVoix_Inscrit2 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime2 = (BigDecimal) dis.readObject();

					this.NumeroPanneau3 = readInteger(dis);

					this.Sexe3 = readString(dis);

					this.Nom3 = readString(dis);

					this.Prenom3 = readString(dis);

					this.Voix3 = readInteger(dis);

					this.PourcentageVoix_Inscrit3 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime3 = (BigDecimal) dis.readObject();

					this.NumeroPanneau4 = readInteger(dis);

					this.Sexe4 = readString(dis);

					this.Nom4 = readString(dis);

					this.Prenom4 = readString(dis);

					this.Voix4 = readInteger(dis);

					this.PourcentageVoix_Inscrit4 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime4 = (BigDecimal) dis.readObject();

					this.NumeroPanneau5 = readInteger(dis);

					this.Sexe5 = readString(dis);

					this.Nom5 = readString(dis);

					this.Prenom5 = readString(dis);

					this.Voix5 = readInteger(dis);

					this.PourcentageVoix_Inscrit5 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime5 = (BigDecimal) dis.readObject();

					this.NumeroPanneau6 = readInteger(dis);

					this.Sexe6 = readString(dis);

					this.Nom6 = readString(dis);

					this.Prenom6 = readString(dis);

					this.Voix6 = readInteger(dis);

					this.PourcentageVoix_Inscrit6 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime6 = (BigDecimal) dis.readObject();

					this.NumeroPanneau7 = readInteger(dis);

					this.Sexe7 = readString(dis);

					this.Nom7 = readString(dis);

					this.Prenom7 = readString(dis);

					this.Voix7 = readInteger(dis);

					this.PourcentageVoix_Inscrit7 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime7 = (BigDecimal) dis.readObject();

					this.NumeroPanneau8 = readInteger(dis);

					this.Sexe8 = readString(dis);

					this.Nom8 = readString(dis);

					this.Prenom8 = readString(dis);

					this.Voix8 = readInteger(dis);

					this.PourcentageVoix_Inscrit8 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime8 = (BigDecimal) dis.readObject();

					this.NumeroPanneau9 = readInteger(dis);

					this.Sexe9 = readString(dis);

					this.Nom9 = readString(dis);

					this.Prenom9 = readString(dis);

					this.Voix9 = readInteger(dis);

					this.PourcentageVoix_Inscrit9 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime9 = (BigDecimal) dis.readObject();

					this.NumeroPanneau10 = readInteger(dis);

					this.Sexe10 = readString(dis);

					this.Nom10 = readString(dis);

					this.Prenom10 = readString(dis);

					this.Voix10 = readInteger(dis);

					this.PourcentageVoix_Inscrit10 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime10 = (BigDecimal) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_VoteElection2017) {

				try {

					int length = 0;

					this.Id = dis.readInt();

					this.CodeDepartement = readString(dis);

					this.LibelleDepartement = readString(dis);

					this.CodeCirconscription = readString(dis);

					this.LibelleCirconscription = readString(dis);

					this.Inscrit = readInteger(dis);

					this.Abstention = readInteger(dis);

					this.PourcentageAbstention_Inscrit = (BigDecimal) dis.readObject();

					this.Votant = readInteger(dis);

					this.PourcentageVotant_Inscrit = (BigDecimal) dis.readObject();

					this.VoteBlanc = readInteger(dis);

					this.PourcentageBlanc_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageBlanc_Votant = (BigDecimal) dis.readObject();

					this.VoteNul = readInteger(dis);

					this.PourcentageNul_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageNul_Votant = (BigDecimal) dis.readObject();

					this.Exprime = readInteger(dis);

					this.PourcentageExprime_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageExprime_Votant = (BigDecimal) dis.readObject();

					this.NumeroPanneau = readInteger(dis);

					this.Sexe = readString(dis);

					this.Nom = readString(dis);

					this.Prenom = readString(dis);

					this.Voix = readInteger(dis);

					this.PourcentageVoix_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime = (BigDecimal) dis.readObject();

					this.NumeroPanneau1 = readInteger(dis);

					this.Sexe1 = readString(dis);

					this.Nom1 = readString(dis);

					this.Prenom1 = readString(dis);

					this.Voix1 = readInteger(dis);

					this.PourcentageVoix_Inscrit1 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime1 = (BigDecimal) dis.readObject();

					this.NumeroPanneau2 = readInteger(dis);

					this.Sexe2 = readString(dis);

					this.Nom2 = readString(dis);

					this.Prenom2 = readString(dis);

					this.Voix2 = readInteger(dis);

					this.PourcentageVoix_Inscrit2 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime2 = (BigDecimal) dis.readObject();

					this.NumeroPanneau3 = readInteger(dis);

					this.Sexe3 = readString(dis);

					this.Nom3 = readString(dis);

					this.Prenom3 = readString(dis);

					this.Voix3 = readInteger(dis);

					this.PourcentageVoix_Inscrit3 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime3 = (BigDecimal) dis.readObject();

					this.NumeroPanneau4 = readInteger(dis);

					this.Sexe4 = readString(dis);

					this.Nom4 = readString(dis);

					this.Prenom4 = readString(dis);

					this.Voix4 = readInteger(dis);

					this.PourcentageVoix_Inscrit4 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime4 = (BigDecimal) dis.readObject();

					this.NumeroPanneau5 = readInteger(dis);

					this.Sexe5 = readString(dis);

					this.Nom5 = readString(dis);

					this.Prenom5 = readString(dis);

					this.Voix5 = readInteger(dis);

					this.PourcentageVoix_Inscrit5 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime5 = (BigDecimal) dis.readObject();

					this.NumeroPanneau6 = readInteger(dis);

					this.Sexe6 = readString(dis);

					this.Nom6 = readString(dis);

					this.Prenom6 = readString(dis);

					this.Voix6 = readInteger(dis);

					this.PourcentageVoix_Inscrit6 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime6 = (BigDecimal) dis.readObject();

					this.NumeroPanneau7 = readInteger(dis);

					this.Sexe7 = readString(dis);

					this.Nom7 = readString(dis);

					this.Prenom7 = readString(dis);

					this.Voix7 = readInteger(dis);

					this.PourcentageVoix_Inscrit7 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime7 = (BigDecimal) dis.readObject();

					this.NumeroPanneau8 = readInteger(dis);

					this.Sexe8 = readString(dis);

					this.Nom8 = readString(dis);

					this.Prenom8 = readString(dis);

					this.Voix8 = readInteger(dis);

					this.PourcentageVoix_Inscrit8 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime8 = (BigDecimal) dis.readObject();

					this.NumeroPanneau9 = readInteger(dis);

					this.Sexe9 = readString(dis);

					this.Nom9 = readString(dis);

					this.Prenom9 = readString(dis);

					this.Voix9 = readInteger(dis);

					this.PourcentageVoix_Inscrit9 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime9 = (BigDecimal) dis.readObject();

					this.NumeroPanneau10 = readInteger(dis);

					this.Sexe10 = readString(dis);

					this.Nom10 = readString(dis);

					this.Prenom10 = readString(dis);

					this.Voix10 = readInteger(dis);

					this.PourcentageVoix_Inscrit10 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime10 = (BigDecimal) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.CodeDepartement, dos);

				// String

				writeString(this.LibelleDepartement, dos);

				// String

				writeString(this.CodeCirconscription, dos);

				// String

				writeString(this.LibelleCirconscription, dos);

				// Integer

				writeInteger(this.Inscrit, dos);

				// Integer

				writeInteger(this.Abstention, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageAbstention_Inscrit);

				// Integer

				writeInteger(this.Votant, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVotant_Inscrit);

				// Integer

				writeInteger(this.VoteBlanc, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Votant);

				// Integer

				writeInteger(this.VoteNul, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Votant);

				// Integer

				writeInteger(this.Exprime, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Votant);

				// Integer

				writeInteger(this.NumeroPanneau, dos);

				// String

				writeString(this.Sexe, dos);

				// String

				writeString(this.Nom, dos);

				// String

				writeString(this.Prenom, dos);

				// Integer

				writeInteger(this.Voix, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime);

				// Integer

				writeInteger(this.NumeroPanneau1, dos);

				// String

				writeString(this.Sexe1, dos);

				// String

				writeString(this.Nom1, dos);

				// String

				writeString(this.Prenom1, dos);

				// Integer

				writeInteger(this.Voix1, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit1);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime1);

				// Integer

				writeInteger(this.NumeroPanneau2, dos);

				// String

				writeString(this.Sexe2, dos);

				// String

				writeString(this.Nom2, dos);

				// String

				writeString(this.Prenom2, dos);

				// Integer

				writeInteger(this.Voix2, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit2);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime2);

				// Integer

				writeInteger(this.NumeroPanneau3, dos);

				// String

				writeString(this.Sexe3, dos);

				// String

				writeString(this.Nom3, dos);

				// String

				writeString(this.Prenom3, dos);

				// Integer

				writeInteger(this.Voix3, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit3);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime3);

				// Integer

				writeInteger(this.NumeroPanneau4, dos);

				// String

				writeString(this.Sexe4, dos);

				// String

				writeString(this.Nom4, dos);

				// String

				writeString(this.Prenom4, dos);

				// Integer

				writeInteger(this.Voix4, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit4);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime4);

				// Integer

				writeInteger(this.NumeroPanneau5, dos);

				// String

				writeString(this.Sexe5, dos);

				// String

				writeString(this.Nom5, dos);

				// String

				writeString(this.Prenom5, dos);

				// Integer

				writeInteger(this.Voix5, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit5);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime5);

				// Integer

				writeInteger(this.NumeroPanneau6, dos);

				// String

				writeString(this.Sexe6, dos);

				// String

				writeString(this.Nom6, dos);

				// String

				writeString(this.Prenom6, dos);

				// Integer

				writeInteger(this.Voix6, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit6);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime6);

				// Integer

				writeInteger(this.NumeroPanneau7, dos);

				// String

				writeString(this.Sexe7, dos);

				// String

				writeString(this.Nom7, dos);

				// String

				writeString(this.Prenom7, dos);

				// Integer

				writeInteger(this.Voix7, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit7);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime7);

				// Integer

				writeInteger(this.NumeroPanneau8, dos);

				// String

				writeString(this.Sexe8, dos);

				// String

				writeString(this.Nom8, dos);

				// String

				writeString(this.Prenom8, dos);

				// Integer

				writeInteger(this.Voix8, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit8);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime8);

				// Integer

				writeInteger(this.NumeroPanneau9, dos);

				// String

				writeString(this.Sexe9, dos);

				// String

				writeString(this.Nom9, dos);

				// String

				writeString(this.Prenom9, dos);

				// Integer

				writeInteger(this.Voix9, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit9);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime9);

				// Integer

				writeInteger(this.NumeroPanneau10, dos);

				// String

				writeString(this.Sexe10, dos);

				// String

				writeString(this.Nom10, dos);

				// String

				writeString(this.Prenom10, dos);

				// Integer

				writeInteger(this.Voix10, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit10);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime10);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.Id);

				// String

				writeString(this.CodeDepartement, dos);

				// String

				writeString(this.LibelleDepartement, dos);

				// String

				writeString(this.CodeCirconscription, dos);

				// String

				writeString(this.LibelleCirconscription, dos);

				// Integer

				writeInteger(this.Inscrit, dos);

				// Integer

				writeInteger(this.Abstention, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageAbstention_Inscrit);

				// Integer

				writeInteger(this.Votant, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVotant_Inscrit);

				// Integer

				writeInteger(this.VoteBlanc, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Votant);

				// Integer

				writeInteger(this.VoteNul, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Votant);

				// Integer

				writeInteger(this.Exprime, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Votant);

				// Integer

				writeInteger(this.NumeroPanneau, dos);

				// String

				writeString(this.Sexe, dos);

				// String

				writeString(this.Nom, dos);

				// String

				writeString(this.Prenom, dos);

				// Integer

				writeInteger(this.Voix, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime);

				// Integer

				writeInteger(this.NumeroPanneau1, dos);

				// String

				writeString(this.Sexe1, dos);

				// String

				writeString(this.Nom1, dos);

				// String

				writeString(this.Prenom1, dos);

				// Integer

				writeInteger(this.Voix1, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit1);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime1);

				// Integer

				writeInteger(this.NumeroPanneau2, dos);

				// String

				writeString(this.Sexe2, dos);

				// String

				writeString(this.Nom2, dos);

				// String

				writeString(this.Prenom2, dos);

				// Integer

				writeInteger(this.Voix2, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit2);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime2);

				// Integer

				writeInteger(this.NumeroPanneau3, dos);

				// String

				writeString(this.Sexe3, dos);

				// String

				writeString(this.Nom3, dos);

				// String

				writeString(this.Prenom3, dos);

				// Integer

				writeInteger(this.Voix3, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit3);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime3);

				// Integer

				writeInteger(this.NumeroPanneau4, dos);

				// String

				writeString(this.Sexe4, dos);

				// String

				writeString(this.Nom4, dos);

				// String

				writeString(this.Prenom4, dos);

				// Integer

				writeInteger(this.Voix4, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit4);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime4);

				// Integer

				writeInteger(this.NumeroPanneau5, dos);

				// String

				writeString(this.Sexe5, dos);

				// String

				writeString(this.Nom5, dos);

				// String

				writeString(this.Prenom5, dos);

				// Integer

				writeInteger(this.Voix5, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit5);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime5);

				// Integer

				writeInteger(this.NumeroPanneau6, dos);

				// String

				writeString(this.Sexe6, dos);

				// String

				writeString(this.Nom6, dos);

				// String

				writeString(this.Prenom6, dos);

				// Integer

				writeInteger(this.Voix6, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit6);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime6);

				// Integer

				writeInteger(this.NumeroPanneau7, dos);

				// String

				writeString(this.Sexe7, dos);

				// String

				writeString(this.Nom7, dos);

				// String

				writeString(this.Prenom7, dos);

				// Integer

				writeInteger(this.Voix7, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit7);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime7);

				// Integer

				writeInteger(this.NumeroPanneau8, dos);

				// String

				writeString(this.Sexe8, dos);

				// String

				writeString(this.Nom8, dos);

				// String

				writeString(this.Prenom8, dos);

				// Integer

				writeInteger(this.Voix8, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit8);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime8);

				// Integer

				writeInteger(this.NumeroPanneau9, dos);

				// String

				writeString(this.Sexe9, dos);

				// String

				writeString(this.Nom9, dos);

				// String

				writeString(this.Prenom9, dos);

				// Integer

				writeInteger(this.Voix9, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit9);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime9);

				// Integer

				writeInteger(this.NumeroPanneau10, dos);

				// String

				writeString(this.Sexe10, dos);

				// String

				writeString(this.Nom10, dos);

				// String

				writeString(this.Prenom10, dos);

				// Integer

				writeInteger(this.Voix10, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit10);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime10);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Id=" + String.valueOf(Id));
			sb.append(",CodeDepartement=" + CodeDepartement);
			sb.append(",LibelleDepartement=" + LibelleDepartement);
			sb.append(",CodeCirconscription=" + CodeCirconscription);
			sb.append(",LibelleCirconscription=" + LibelleCirconscription);
			sb.append(",Inscrit=" + String.valueOf(Inscrit));
			sb.append(",Abstention=" + String.valueOf(Abstention));
			sb.append(",PourcentageAbstention_Inscrit=" + String.valueOf(PourcentageAbstention_Inscrit));
			sb.append(",Votant=" + String.valueOf(Votant));
			sb.append(",PourcentageVotant_Inscrit=" + String.valueOf(PourcentageVotant_Inscrit));
			sb.append(",VoteBlanc=" + String.valueOf(VoteBlanc));
			sb.append(",PourcentageBlanc_Inscrit=" + String.valueOf(PourcentageBlanc_Inscrit));
			sb.append(",PourcentageBlanc_Votant=" + String.valueOf(PourcentageBlanc_Votant));
			sb.append(",VoteNul=" + String.valueOf(VoteNul));
			sb.append(",PourcentageNul_Inscrit=" + String.valueOf(PourcentageNul_Inscrit));
			sb.append(",PourcentageNul_Votant=" + String.valueOf(PourcentageNul_Votant));
			sb.append(",Exprime=" + String.valueOf(Exprime));
			sb.append(",PourcentageExprime_Inscrit=" + String.valueOf(PourcentageExprime_Inscrit));
			sb.append(",PourcentageExprime_Votant=" + String.valueOf(PourcentageExprime_Votant));
			sb.append(",NumeroPanneau=" + String.valueOf(NumeroPanneau));
			sb.append(",Sexe=" + Sexe);
			sb.append(",Nom=" + Nom);
			sb.append(",Prenom=" + Prenom);
			sb.append(",Voix=" + String.valueOf(Voix));
			sb.append(",PourcentageVoix_Inscrit=" + String.valueOf(PourcentageVoix_Inscrit));
			sb.append(",PourcentageVoix_Exprime=" + String.valueOf(PourcentageVoix_Exprime));
			sb.append(",NumeroPanneau1=" + String.valueOf(NumeroPanneau1));
			sb.append(",Sexe1=" + Sexe1);
			sb.append(",Nom1=" + Nom1);
			sb.append(",Prenom1=" + Prenom1);
			sb.append(",Voix1=" + String.valueOf(Voix1));
			sb.append(",PourcentageVoix_Inscrit1=" + String.valueOf(PourcentageVoix_Inscrit1));
			sb.append(",PourcentageVoix_Exprime1=" + String.valueOf(PourcentageVoix_Exprime1));
			sb.append(",NumeroPanneau2=" + String.valueOf(NumeroPanneau2));
			sb.append(",Sexe2=" + Sexe2);
			sb.append(",Nom2=" + Nom2);
			sb.append(",Prenom2=" + Prenom2);
			sb.append(",Voix2=" + String.valueOf(Voix2));
			sb.append(",PourcentageVoix_Inscrit2=" + String.valueOf(PourcentageVoix_Inscrit2));
			sb.append(",PourcentageVoix_Exprime2=" + String.valueOf(PourcentageVoix_Exprime2));
			sb.append(",NumeroPanneau3=" + String.valueOf(NumeroPanneau3));
			sb.append(",Sexe3=" + Sexe3);
			sb.append(",Nom3=" + Nom3);
			sb.append(",Prenom3=" + Prenom3);
			sb.append(",Voix3=" + String.valueOf(Voix3));
			sb.append(",PourcentageVoix_Inscrit3=" + String.valueOf(PourcentageVoix_Inscrit3));
			sb.append(",PourcentageVoix_Exprime3=" + String.valueOf(PourcentageVoix_Exprime3));
			sb.append(",NumeroPanneau4=" + String.valueOf(NumeroPanneau4));
			sb.append(",Sexe4=" + Sexe4);
			sb.append(",Nom4=" + Nom4);
			sb.append(",Prenom4=" + Prenom4);
			sb.append(",Voix4=" + String.valueOf(Voix4));
			sb.append(",PourcentageVoix_Inscrit4=" + String.valueOf(PourcentageVoix_Inscrit4));
			sb.append(",PourcentageVoix_Exprime4=" + String.valueOf(PourcentageVoix_Exprime4));
			sb.append(",NumeroPanneau5=" + String.valueOf(NumeroPanneau5));
			sb.append(",Sexe5=" + Sexe5);
			sb.append(",Nom5=" + Nom5);
			sb.append(",Prenom5=" + Prenom5);
			sb.append(",Voix5=" + String.valueOf(Voix5));
			sb.append(",PourcentageVoix_Inscrit5=" + String.valueOf(PourcentageVoix_Inscrit5));
			sb.append(",PourcentageVoix_Exprime5=" + String.valueOf(PourcentageVoix_Exprime5));
			sb.append(",NumeroPanneau6=" + String.valueOf(NumeroPanneau6));
			sb.append(",Sexe6=" + Sexe6);
			sb.append(",Nom6=" + Nom6);
			sb.append(",Prenom6=" + Prenom6);
			sb.append(",Voix6=" + String.valueOf(Voix6));
			sb.append(",PourcentageVoix_Inscrit6=" + String.valueOf(PourcentageVoix_Inscrit6));
			sb.append(",PourcentageVoix_Exprime6=" + String.valueOf(PourcentageVoix_Exprime6));
			sb.append(",NumeroPanneau7=" + String.valueOf(NumeroPanneau7));
			sb.append(",Sexe7=" + Sexe7);
			sb.append(",Nom7=" + Nom7);
			sb.append(",Prenom7=" + Prenom7);
			sb.append(",Voix7=" + String.valueOf(Voix7));
			sb.append(",PourcentageVoix_Inscrit7=" + String.valueOf(PourcentageVoix_Inscrit7));
			sb.append(",PourcentageVoix_Exprime7=" + String.valueOf(PourcentageVoix_Exprime7));
			sb.append(",NumeroPanneau8=" + String.valueOf(NumeroPanneau8));
			sb.append(",Sexe8=" + Sexe8);
			sb.append(",Nom8=" + Nom8);
			sb.append(",Prenom8=" + Prenom8);
			sb.append(",Voix8=" + String.valueOf(Voix8));
			sb.append(",PourcentageVoix_Inscrit8=" + String.valueOf(PourcentageVoix_Inscrit8));
			sb.append(",PourcentageVoix_Exprime8=" + String.valueOf(PourcentageVoix_Exprime8));
			sb.append(",NumeroPanneau9=" + String.valueOf(NumeroPanneau9));
			sb.append(",Sexe9=" + Sexe9);
			sb.append(",Nom9=" + Nom9);
			sb.append(",Prenom9=" + Prenom9);
			sb.append(",Voix9=" + String.valueOf(Voix9));
			sb.append(",PourcentageVoix_Inscrit9=" + String.valueOf(PourcentageVoix_Inscrit9));
			sb.append(",PourcentageVoix_Exprime9=" + String.valueOf(PourcentageVoix_Exprime9));
			sb.append(",NumeroPanneau10=" + String.valueOf(NumeroPanneau10));
			sb.append(",Sexe10=" + Sexe10);
			sb.append(",Nom10=" + Nom10);
			sb.append(",Prenom10=" + Prenom10);
			sb.append(",Voix10=" + String.valueOf(Voix10));
			sb.append(",PourcentageVoix_Inscrit10=" + String.valueOf(PourcentageVoix_Inscrit10));
			sb.append(",PourcentageVoix_Exprime10=" + String.valueOf(PourcentageVoix_Exprime10));
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
		final static byte[] commonByteArrayLock_DATAMSPR_VoteElection2017 = new byte[0];
		static byte[] commonByteArray_DATAMSPR_VoteElection2017 = new byte[0];

		public String CodeDepartement;

		public String getCodeDepartement() {
			return this.CodeDepartement;
		}

		public String LibelleDepartement;

		public String getLibelleDepartement() {
			return this.LibelleDepartement;
		}

		public String CodeCirconscription;

		public String getCodeCirconscription() {
			return this.CodeCirconscription;
		}

		public String LibelleCirconscription;

		public String getLibelleCirconscription() {
			return this.LibelleCirconscription;
		}

		public Integer Inscrit;

		public Integer getInscrit() {
			return this.Inscrit;
		}

		public Integer Abstention;

		public Integer getAbstention() {
			return this.Abstention;
		}

		public BigDecimal PourcentageAbstention_Inscrit;

		public BigDecimal getPourcentageAbstention_Inscrit() {
			return this.PourcentageAbstention_Inscrit;
		}

		public Integer Votant;

		public Integer getVotant() {
			return this.Votant;
		}

		public BigDecimal PourcentageVotant_Inscrit;

		public BigDecimal getPourcentageVotant_Inscrit() {
			return this.PourcentageVotant_Inscrit;
		}

		public Integer VoteBlanc;

		public Integer getVoteBlanc() {
			return this.VoteBlanc;
		}

		public BigDecimal PourcentageBlanc_Inscrit;

		public BigDecimal getPourcentageBlanc_Inscrit() {
			return this.PourcentageBlanc_Inscrit;
		}

		public BigDecimal PourcentageBlanc_Votant;

		public BigDecimal getPourcentageBlanc_Votant() {
			return this.PourcentageBlanc_Votant;
		}

		public Integer VoteNul;

		public Integer getVoteNul() {
			return this.VoteNul;
		}

		public BigDecimal PourcentageNul_Inscrit;

		public BigDecimal getPourcentageNul_Inscrit() {
			return this.PourcentageNul_Inscrit;
		}

		public BigDecimal PourcentageNul_Votant;

		public BigDecimal getPourcentageNul_Votant() {
			return this.PourcentageNul_Votant;
		}

		public Integer Exprime;

		public Integer getExprime() {
			return this.Exprime;
		}

		public BigDecimal PourcentageExprime_Inscrit;

		public BigDecimal getPourcentageExprime_Inscrit() {
			return this.PourcentageExprime_Inscrit;
		}

		public BigDecimal PourcentageExprime_Votant;

		public BigDecimal getPourcentageExprime_Votant() {
			return this.PourcentageExprime_Votant;
		}

		public Integer NumeroPanneau;

		public Integer getNumeroPanneau() {
			return this.NumeroPanneau;
		}

		public String Sexe;

		public String getSexe() {
			return this.Sexe;
		}

		public String Nom;

		public String getNom() {
			return this.Nom;
		}

		public String Prenom;

		public String getPrenom() {
			return this.Prenom;
		}

		public Integer Voix;

		public Integer getVoix() {
			return this.Voix;
		}

		public BigDecimal PourcentageVoix_Inscrit;

		public BigDecimal getPourcentageVoix_Inscrit() {
			return this.PourcentageVoix_Inscrit;
		}

		public BigDecimal PourcentageVoix_Exprime;

		public BigDecimal getPourcentageVoix_Exprime() {
			return this.PourcentageVoix_Exprime;
		}

		public Integer NumeroPanneau1;

		public Integer getNumeroPanneau1() {
			return this.NumeroPanneau1;
		}

		public String Sexe1;

		public String getSexe1() {
			return this.Sexe1;
		}

		public String Nom1;

		public String getNom1() {
			return this.Nom1;
		}

		public String Prenom1;

		public String getPrenom1() {
			return this.Prenom1;
		}

		public Integer Voix1;

		public Integer getVoix1() {
			return this.Voix1;
		}

		public BigDecimal PourcentageVoix_Inscrit1;

		public BigDecimal getPourcentageVoix_Inscrit1() {
			return this.PourcentageVoix_Inscrit1;
		}

		public BigDecimal PourcentageVoix_Exprime1;

		public BigDecimal getPourcentageVoix_Exprime1() {
			return this.PourcentageVoix_Exprime1;
		}

		public Integer NumeroPanneau2;

		public Integer getNumeroPanneau2() {
			return this.NumeroPanneau2;
		}

		public String Sexe2;

		public String getSexe2() {
			return this.Sexe2;
		}

		public String Nom2;

		public String getNom2() {
			return this.Nom2;
		}

		public String Prenom2;

		public String getPrenom2() {
			return this.Prenom2;
		}

		public Integer Voix2;

		public Integer getVoix2() {
			return this.Voix2;
		}

		public BigDecimal PourcentageVoix_Inscrit2;

		public BigDecimal getPourcentageVoix_Inscrit2() {
			return this.PourcentageVoix_Inscrit2;
		}

		public BigDecimal PourcentageVoix_Exprime2;

		public BigDecimal getPourcentageVoix_Exprime2() {
			return this.PourcentageVoix_Exprime2;
		}

		public Integer NumeroPanneau3;

		public Integer getNumeroPanneau3() {
			return this.NumeroPanneau3;
		}

		public String Sexe3;

		public String getSexe3() {
			return this.Sexe3;
		}

		public String Nom3;

		public String getNom3() {
			return this.Nom3;
		}

		public String Prenom3;

		public String getPrenom3() {
			return this.Prenom3;
		}

		public Integer Voix3;

		public Integer getVoix3() {
			return this.Voix3;
		}

		public BigDecimal PourcentageVoix_Inscrit3;

		public BigDecimal getPourcentageVoix_Inscrit3() {
			return this.PourcentageVoix_Inscrit3;
		}

		public BigDecimal PourcentageVoix_Exprime3;

		public BigDecimal getPourcentageVoix_Exprime3() {
			return this.PourcentageVoix_Exprime3;
		}

		public Integer NumeroPanneau4;

		public Integer getNumeroPanneau4() {
			return this.NumeroPanneau4;
		}

		public String Sexe4;

		public String getSexe4() {
			return this.Sexe4;
		}

		public String Nom4;

		public String getNom4() {
			return this.Nom4;
		}

		public String Prenom4;

		public String getPrenom4() {
			return this.Prenom4;
		}

		public Integer Voix4;

		public Integer getVoix4() {
			return this.Voix4;
		}

		public BigDecimal PourcentageVoix_Inscrit4;

		public BigDecimal getPourcentageVoix_Inscrit4() {
			return this.PourcentageVoix_Inscrit4;
		}

		public BigDecimal PourcentageVoix_Exprime4;

		public BigDecimal getPourcentageVoix_Exprime4() {
			return this.PourcentageVoix_Exprime4;
		}

		public Integer NumeroPanneau5;

		public Integer getNumeroPanneau5() {
			return this.NumeroPanneau5;
		}

		public String Sexe5;

		public String getSexe5() {
			return this.Sexe5;
		}

		public String Nom5;

		public String getNom5() {
			return this.Nom5;
		}

		public String Prenom5;

		public String getPrenom5() {
			return this.Prenom5;
		}

		public Integer Voix5;

		public Integer getVoix5() {
			return this.Voix5;
		}

		public BigDecimal PourcentageVoix_Inscrit5;

		public BigDecimal getPourcentageVoix_Inscrit5() {
			return this.PourcentageVoix_Inscrit5;
		}

		public BigDecimal PourcentageVoix_Exprime5;

		public BigDecimal getPourcentageVoix_Exprime5() {
			return this.PourcentageVoix_Exprime5;
		}

		public Integer NumeroPanneau6;

		public Integer getNumeroPanneau6() {
			return this.NumeroPanneau6;
		}

		public String Sexe6;

		public String getSexe6() {
			return this.Sexe6;
		}

		public String Nom6;

		public String getNom6() {
			return this.Nom6;
		}

		public String Prenom6;

		public String getPrenom6() {
			return this.Prenom6;
		}

		public Integer Voix6;

		public Integer getVoix6() {
			return this.Voix6;
		}

		public BigDecimal PourcentageVoix_Inscrit6;

		public BigDecimal getPourcentageVoix_Inscrit6() {
			return this.PourcentageVoix_Inscrit6;
		}

		public BigDecimal PourcentageVoix_Exprime6;

		public BigDecimal getPourcentageVoix_Exprime6() {
			return this.PourcentageVoix_Exprime6;
		}

		public Integer NumeroPanneau7;

		public Integer getNumeroPanneau7() {
			return this.NumeroPanneau7;
		}

		public String Sexe7;

		public String getSexe7() {
			return this.Sexe7;
		}

		public String Nom7;

		public String getNom7() {
			return this.Nom7;
		}

		public String Prenom7;

		public String getPrenom7() {
			return this.Prenom7;
		}

		public Integer Voix7;

		public Integer getVoix7() {
			return this.Voix7;
		}

		public BigDecimal PourcentageVoix_Inscrit7;

		public BigDecimal getPourcentageVoix_Inscrit7() {
			return this.PourcentageVoix_Inscrit7;
		}

		public BigDecimal PourcentageVoix_Exprime7;

		public BigDecimal getPourcentageVoix_Exprime7() {
			return this.PourcentageVoix_Exprime7;
		}

		public Integer NumeroPanneau8;

		public Integer getNumeroPanneau8() {
			return this.NumeroPanneau8;
		}

		public String Sexe8;

		public String getSexe8() {
			return this.Sexe8;
		}

		public String Nom8;

		public String getNom8() {
			return this.Nom8;
		}

		public String Prenom8;

		public String getPrenom8() {
			return this.Prenom8;
		}

		public Integer Voix8;

		public Integer getVoix8() {
			return this.Voix8;
		}

		public BigDecimal PourcentageVoix_Inscrit8;

		public BigDecimal getPourcentageVoix_Inscrit8() {
			return this.PourcentageVoix_Inscrit8;
		}

		public BigDecimal PourcentageVoix_Exprime8;

		public BigDecimal getPourcentageVoix_Exprime8() {
			return this.PourcentageVoix_Exprime8;
		}

		public Integer NumeroPanneau9;

		public Integer getNumeroPanneau9() {
			return this.NumeroPanneau9;
		}

		public String Sexe9;

		public String getSexe9() {
			return this.Sexe9;
		}

		public String Nom9;

		public String getNom9() {
			return this.Nom9;
		}

		public String Prenom9;

		public String getPrenom9() {
			return this.Prenom9;
		}

		public Integer Voix9;

		public Integer getVoix9() {
			return this.Voix9;
		}

		public BigDecimal PourcentageVoix_Inscrit9;

		public BigDecimal getPourcentageVoix_Inscrit9() {
			return this.PourcentageVoix_Inscrit9;
		}

		public BigDecimal PourcentageVoix_Exprime9;

		public BigDecimal getPourcentageVoix_Exprime9() {
			return this.PourcentageVoix_Exprime9;
		}

		public Integer NumeroPanneau10;

		public Integer getNumeroPanneau10() {
			return this.NumeroPanneau10;
		}

		public String Sexe10;

		public String getSexe10() {
			return this.Sexe10;
		}

		public String Nom10;

		public String getNom10() {
			return this.Nom10;
		}

		public String Prenom10;

		public String getPrenom10() {
			return this.Prenom10;
		}

		public Integer Voix10;

		public Integer getVoix10() {
			return this.Voix10;
		}

		public BigDecimal PourcentageVoix_Inscrit10;

		public BigDecimal getPourcentageVoix_Inscrit10() {
			return this.PourcentageVoix_Inscrit10;
		}

		public BigDecimal PourcentageVoix_Exprime10;

		public BigDecimal getPourcentageVoix_Exprime10() {
			return this.PourcentageVoix_Exprime10;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_DATAMSPR_VoteElection2017.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_VoteElection2017.length == 0) {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_DATAMSPR_VoteElection2017, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_VoteElection2017, 0, length, utf8Charset);
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
				if (length > commonByteArray_DATAMSPR_VoteElection2017.length) {
					if (length < 1024 && commonByteArray_DATAMSPR_VoteElection2017.length == 0) {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[1024];
					} else {
						commonByteArray_DATAMSPR_VoteElection2017 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_DATAMSPR_VoteElection2017, 0, length);
				strReturn = new String(commonByteArray_DATAMSPR_VoteElection2017, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_DATAMSPR_VoteElection2017) {

				try {

					int length = 0;

					this.CodeDepartement = readString(dis);

					this.LibelleDepartement = readString(dis);

					this.CodeCirconscription = readString(dis);

					this.LibelleCirconscription = readString(dis);

					this.Inscrit = readInteger(dis);

					this.Abstention = readInteger(dis);

					this.PourcentageAbstention_Inscrit = (BigDecimal) dis.readObject();

					this.Votant = readInteger(dis);

					this.PourcentageVotant_Inscrit = (BigDecimal) dis.readObject();

					this.VoteBlanc = readInteger(dis);

					this.PourcentageBlanc_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageBlanc_Votant = (BigDecimal) dis.readObject();

					this.VoteNul = readInteger(dis);

					this.PourcentageNul_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageNul_Votant = (BigDecimal) dis.readObject();

					this.Exprime = readInteger(dis);

					this.PourcentageExprime_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageExprime_Votant = (BigDecimal) dis.readObject();

					this.NumeroPanneau = readInteger(dis);

					this.Sexe = readString(dis);

					this.Nom = readString(dis);

					this.Prenom = readString(dis);

					this.Voix = readInteger(dis);

					this.PourcentageVoix_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime = (BigDecimal) dis.readObject();

					this.NumeroPanneau1 = readInteger(dis);

					this.Sexe1 = readString(dis);

					this.Nom1 = readString(dis);

					this.Prenom1 = readString(dis);

					this.Voix1 = readInteger(dis);

					this.PourcentageVoix_Inscrit1 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime1 = (BigDecimal) dis.readObject();

					this.NumeroPanneau2 = readInteger(dis);

					this.Sexe2 = readString(dis);

					this.Nom2 = readString(dis);

					this.Prenom2 = readString(dis);

					this.Voix2 = readInteger(dis);

					this.PourcentageVoix_Inscrit2 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime2 = (BigDecimal) dis.readObject();

					this.NumeroPanneau3 = readInteger(dis);

					this.Sexe3 = readString(dis);

					this.Nom3 = readString(dis);

					this.Prenom3 = readString(dis);

					this.Voix3 = readInteger(dis);

					this.PourcentageVoix_Inscrit3 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime3 = (BigDecimal) dis.readObject();

					this.NumeroPanneau4 = readInteger(dis);

					this.Sexe4 = readString(dis);

					this.Nom4 = readString(dis);

					this.Prenom4 = readString(dis);

					this.Voix4 = readInteger(dis);

					this.PourcentageVoix_Inscrit4 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime4 = (BigDecimal) dis.readObject();

					this.NumeroPanneau5 = readInteger(dis);

					this.Sexe5 = readString(dis);

					this.Nom5 = readString(dis);

					this.Prenom5 = readString(dis);

					this.Voix5 = readInteger(dis);

					this.PourcentageVoix_Inscrit5 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime5 = (BigDecimal) dis.readObject();

					this.NumeroPanneau6 = readInteger(dis);

					this.Sexe6 = readString(dis);

					this.Nom6 = readString(dis);

					this.Prenom6 = readString(dis);

					this.Voix6 = readInteger(dis);

					this.PourcentageVoix_Inscrit6 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime6 = (BigDecimal) dis.readObject();

					this.NumeroPanneau7 = readInteger(dis);

					this.Sexe7 = readString(dis);

					this.Nom7 = readString(dis);

					this.Prenom7 = readString(dis);

					this.Voix7 = readInteger(dis);

					this.PourcentageVoix_Inscrit7 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime7 = (BigDecimal) dis.readObject();

					this.NumeroPanneau8 = readInteger(dis);

					this.Sexe8 = readString(dis);

					this.Nom8 = readString(dis);

					this.Prenom8 = readString(dis);

					this.Voix8 = readInteger(dis);

					this.PourcentageVoix_Inscrit8 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime8 = (BigDecimal) dis.readObject();

					this.NumeroPanneau9 = readInteger(dis);

					this.Sexe9 = readString(dis);

					this.Nom9 = readString(dis);

					this.Prenom9 = readString(dis);

					this.Voix9 = readInteger(dis);

					this.PourcentageVoix_Inscrit9 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime9 = (BigDecimal) dis.readObject();

					this.NumeroPanneau10 = readInteger(dis);

					this.Sexe10 = readString(dis);

					this.Nom10 = readString(dis);

					this.Prenom10 = readString(dis);

					this.Voix10 = readInteger(dis);

					this.PourcentageVoix_Inscrit10 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime10 = (BigDecimal) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_DATAMSPR_VoteElection2017) {

				try {

					int length = 0;

					this.CodeDepartement = readString(dis);

					this.LibelleDepartement = readString(dis);

					this.CodeCirconscription = readString(dis);

					this.LibelleCirconscription = readString(dis);

					this.Inscrit = readInteger(dis);

					this.Abstention = readInteger(dis);

					this.PourcentageAbstention_Inscrit = (BigDecimal) dis.readObject();

					this.Votant = readInteger(dis);

					this.PourcentageVotant_Inscrit = (BigDecimal) dis.readObject();

					this.VoteBlanc = readInteger(dis);

					this.PourcentageBlanc_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageBlanc_Votant = (BigDecimal) dis.readObject();

					this.VoteNul = readInteger(dis);

					this.PourcentageNul_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageNul_Votant = (BigDecimal) dis.readObject();

					this.Exprime = readInteger(dis);

					this.PourcentageExprime_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageExprime_Votant = (BigDecimal) dis.readObject();

					this.NumeroPanneau = readInteger(dis);

					this.Sexe = readString(dis);

					this.Nom = readString(dis);

					this.Prenom = readString(dis);

					this.Voix = readInteger(dis);

					this.PourcentageVoix_Inscrit = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime = (BigDecimal) dis.readObject();

					this.NumeroPanneau1 = readInteger(dis);

					this.Sexe1 = readString(dis);

					this.Nom1 = readString(dis);

					this.Prenom1 = readString(dis);

					this.Voix1 = readInteger(dis);

					this.PourcentageVoix_Inscrit1 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime1 = (BigDecimal) dis.readObject();

					this.NumeroPanneau2 = readInteger(dis);

					this.Sexe2 = readString(dis);

					this.Nom2 = readString(dis);

					this.Prenom2 = readString(dis);

					this.Voix2 = readInteger(dis);

					this.PourcentageVoix_Inscrit2 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime2 = (BigDecimal) dis.readObject();

					this.NumeroPanneau3 = readInteger(dis);

					this.Sexe3 = readString(dis);

					this.Nom3 = readString(dis);

					this.Prenom3 = readString(dis);

					this.Voix3 = readInteger(dis);

					this.PourcentageVoix_Inscrit3 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime3 = (BigDecimal) dis.readObject();

					this.NumeroPanneau4 = readInteger(dis);

					this.Sexe4 = readString(dis);

					this.Nom4 = readString(dis);

					this.Prenom4 = readString(dis);

					this.Voix4 = readInteger(dis);

					this.PourcentageVoix_Inscrit4 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime4 = (BigDecimal) dis.readObject();

					this.NumeroPanneau5 = readInteger(dis);

					this.Sexe5 = readString(dis);

					this.Nom5 = readString(dis);

					this.Prenom5 = readString(dis);

					this.Voix5 = readInteger(dis);

					this.PourcentageVoix_Inscrit5 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime5 = (BigDecimal) dis.readObject();

					this.NumeroPanneau6 = readInteger(dis);

					this.Sexe6 = readString(dis);

					this.Nom6 = readString(dis);

					this.Prenom6 = readString(dis);

					this.Voix6 = readInteger(dis);

					this.PourcentageVoix_Inscrit6 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime6 = (BigDecimal) dis.readObject();

					this.NumeroPanneau7 = readInteger(dis);

					this.Sexe7 = readString(dis);

					this.Nom7 = readString(dis);

					this.Prenom7 = readString(dis);

					this.Voix7 = readInteger(dis);

					this.PourcentageVoix_Inscrit7 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime7 = (BigDecimal) dis.readObject();

					this.NumeroPanneau8 = readInteger(dis);

					this.Sexe8 = readString(dis);

					this.Nom8 = readString(dis);

					this.Prenom8 = readString(dis);

					this.Voix8 = readInteger(dis);

					this.PourcentageVoix_Inscrit8 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime8 = (BigDecimal) dis.readObject();

					this.NumeroPanneau9 = readInteger(dis);

					this.Sexe9 = readString(dis);

					this.Nom9 = readString(dis);

					this.Prenom9 = readString(dis);

					this.Voix9 = readInteger(dis);

					this.PourcentageVoix_Inscrit9 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime9 = (BigDecimal) dis.readObject();

					this.NumeroPanneau10 = readInteger(dis);

					this.Sexe10 = readString(dis);

					this.Nom10 = readString(dis);

					this.Prenom10 = readString(dis);

					this.Voix10 = readInteger(dis);

					this.PourcentageVoix_Inscrit10 = (BigDecimal) dis.readObject();

					this.PourcentageVoix_Exprime10 = (BigDecimal) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.CodeDepartement, dos);

				// String

				writeString(this.LibelleDepartement, dos);

				// String

				writeString(this.CodeCirconscription, dos);

				// String

				writeString(this.LibelleCirconscription, dos);

				// Integer

				writeInteger(this.Inscrit, dos);

				// Integer

				writeInteger(this.Abstention, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageAbstention_Inscrit);

				// Integer

				writeInteger(this.Votant, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVotant_Inscrit);

				// Integer

				writeInteger(this.VoteBlanc, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Votant);

				// Integer

				writeInteger(this.VoteNul, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Votant);

				// Integer

				writeInteger(this.Exprime, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Votant);

				// Integer

				writeInteger(this.NumeroPanneau, dos);

				// String

				writeString(this.Sexe, dos);

				// String

				writeString(this.Nom, dos);

				// String

				writeString(this.Prenom, dos);

				// Integer

				writeInteger(this.Voix, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime);

				// Integer

				writeInteger(this.NumeroPanneau1, dos);

				// String

				writeString(this.Sexe1, dos);

				// String

				writeString(this.Nom1, dos);

				// String

				writeString(this.Prenom1, dos);

				// Integer

				writeInteger(this.Voix1, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit1);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime1);

				// Integer

				writeInteger(this.NumeroPanneau2, dos);

				// String

				writeString(this.Sexe2, dos);

				// String

				writeString(this.Nom2, dos);

				// String

				writeString(this.Prenom2, dos);

				// Integer

				writeInteger(this.Voix2, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit2);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime2);

				// Integer

				writeInteger(this.NumeroPanneau3, dos);

				// String

				writeString(this.Sexe3, dos);

				// String

				writeString(this.Nom3, dos);

				// String

				writeString(this.Prenom3, dos);

				// Integer

				writeInteger(this.Voix3, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit3);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime3);

				// Integer

				writeInteger(this.NumeroPanneau4, dos);

				// String

				writeString(this.Sexe4, dos);

				// String

				writeString(this.Nom4, dos);

				// String

				writeString(this.Prenom4, dos);

				// Integer

				writeInteger(this.Voix4, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit4);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime4);

				// Integer

				writeInteger(this.NumeroPanneau5, dos);

				// String

				writeString(this.Sexe5, dos);

				// String

				writeString(this.Nom5, dos);

				// String

				writeString(this.Prenom5, dos);

				// Integer

				writeInteger(this.Voix5, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit5);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime5);

				// Integer

				writeInteger(this.NumeroPanneau6, dos);

				// String

				writeString(this.Sexe6, dos);

				// String

				writeString(this.Nom6, dos);

				// String

				writeString(this.Prenom6, dos);

				// Integer

				writeInteger(this.Voix6, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit6);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime6);

				// Integer

				writeInteger(this.NumeroPanneau7, dos);

				// String

				writeString(this.Sexe7, dos);

				// String

				writeString(this.Nom7, dos);

				// String

				writeString(this.Prenom7, dos);

				// Integer

				writeInteger(this.Voix7, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit7);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime7);

				// Integer

				writeInteger(this.NumeroPanneau8, dos);

				// String

				writeString(this.Sexe8, dos);

				// String

				writeString(this.Nom8, dos);

				// String

				writeString(this.Prenom8, dos);

				// Integer

				writeInteger(this.Voix8, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit8);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime8);

				// Integer

				writeInteger(this.NumeroPanneau9, dos);

				// String

				writeString(this.Sexe9, dos);

				// String

				writeString(this.Nom9, dos);

				// String

				writeString(this.Prenom9, dos);

				// Integer

				writeInteger(this.Voix9, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit9);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime9);

				// Integer

				writeInteger(this.NumeroPanneau10, dos);

				// String

				writeString(this.Sexe10, dos);

				// String

				writeString(this.Nom10, dos);

				// String

				writeString(this.Prenom10, dos);

				// Integer

				writeInteger(this.Voix10, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit10);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime10);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.CodeDepartement, dos);

				// String

				writeString(this.LibelleDepartement, dos);

				// String

				writeString(this.CodeCirconscription, dos);

				// String

				writeString(this.LibelleCirconscription, dos);

				// Integer

				writeInteger(this.Inscrit, dos);

				// Integer

				writeInteger(this.Abstention, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageAbstention_Inscrit);

				// Integer

				writeInteger(this.Votant, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVotant_Inscrit);

				// Integer

				writeInteger(this.VoteBlanc, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageBlanc_Votant);

				// Integer

				writeInteger(this.VoteNul, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageNul_Votant);

				// Integer

				writeInteger(this.Exprime, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageExprime_Votant);

				// Integer

				writeInteger(this.NumeroPanneau, dos);

				// String

				writeString(this.Sexe, dos);

				// String

				writeString(this.Nom, dos);

				// String

				writeString(this.Prenom, dos);

				// Integer

				writeInteger(this.Voix, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime);

				// Integer

				writeInteger(this.NumeroPanneau1, dos);

				// String

				writeString(this.Sexe1, dos);

				// String

				writeString(this.Nom1, dos);

				// String

				writeString(this.Prenom1, dos);

				// Integer

				writeInteger(this.Voix1, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit1);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime1);

				// Integer

				writeInteger(this.NumeroPanneau2, dos);

				// String

				writeString(this.Sexe2, dos);

				// String

				writeString(this.Nom2, dos);

				// String

				writeString(this.Prenom2, dos);

				// Integer

				writeInteger(this.Voix2, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit2);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime2);

				// Integer

				writeInteger(this.NumeroPanneau3, dos);

				// String

				writeString(this.Sexe3, dos);

				// String

				writeString(this.Nom3, dos);

				// String

				writeString(this.Prenom3, dos);

				// Integer

				writeInteger(this.Voix3, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit3);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime3);

				// Integer

				writeInteger(this.NumeroPanneau4, dos);

				// String

				writeString(this.Sexe4, dos);

				// String

				writeString(this.Nom4, dos);

				// String

				writeString(this.Prenom4, dos);

				// Integer

				writeInteger(this.Voix4, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit4);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime4);

				// Integer

				writeInteger(this.NumeroPanneau5, dos);

				// String

				writeString(this.Sexe5, dos);

				// String

				writeString(this.Nom5, dos);

				// String

				writeString(this.Prenom5, dos);

				// Integer

				writeInteger(this.Voix5, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit5);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime5);

				// Integer

				writeInteger(this.NumeroPanneau6, dos);

				// String

				writeString(this.Sexe6, dos);

				// String

				writeString(this.Nom6, dos);

				// String

				writeString(this.Prenom6, dos);

				// Integer

				writeInteger(this.Voix6, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit6);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime6);

				// Integer

				writeInteger(this.NumeroPanneau7, dos);

				// String

				writeString(this.Sexe7, dos);

				// String

				writeString(this.Nom7, dos);

				// String

				writeString(this.Prenom7, dos);

				// Integer

				writeInteger(this.Voix7, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit7);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime7);

				// Integer

				writeInteger(this.NumeroPanneau8, dos);

				// String

				writeString(this.Sexe8, dos);

				// String

				writeString(this.Nom8, dos);

				// String

				writeString(this.Prenom8, dos);

				// Integer

				writeInteger(this.Voix8, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit8);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime8);

				// Integer

				writeInteger(this.NumeroPanneau9, dos);

				// String

				writeString(this.Sexe9, dos);

				// String

				writeString(this.Nom9, dos);

				// String

				writeString(this.Prenom9, dos);

				// Integer

				writeInteger(this.Voix9, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit9);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime9);

				// Integer

				writeInteger(this.NumeroPanneau10, dos);

				// String

				writeString(this.Sexe10, dos);

				// String

				writeString(this.Nom10, dos);

				// String

				writeString(this.Prenom10, dos);

				// Integer

				writeInteger(this.Voix10, dos);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Inscrit10);

				// BigDecimal

				dos.writeObject(this.PourcentageVoix_Exprime10);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CodeDepartement=" + CodeDepartement);
			sb.append(",LibelleDepartement=" + LibelleDepartement);
			sb.append(",CodeCirconscription=" + CodeCirconscription);
			sb.append(",LibelleCirconscription=" + LibelleCirconscription);
			sb.append(",Inscrit=" + String.valueOf(Inscrit));
			sb.append(",Abstention=" + String.valueOf(Abstention));
			sb.append(",PourcentageAbstention_Inscrit=" + String.valueOf(PourcentageAbstention_Inscrit));
			sb.append(",Votant=" + String.valueOf(Votant));
			sb.append(",PourcentageVotant_Inscrit=" + String.valueOf(PourcentageVotant_Inscrit));
			sb.append(",VoteBlanc=" + String.valueOf(VoteBlanc));
			sb.append(",PourcentageBlanc_Inscrit=" + String.valueOf(PourcentageBlanc_Inscrit));
			sb.append(",PourcentageBlanc_Votant=" + String.valueOf(PourcentageBlanc_Votant));
			sb.append(",VoteNul=" + String.valueOf(VoteNul));
			sb.append(",PourcentageNul_Inscrit=" + String.valueOf(PourcentageNul_Inscrit));
			sb.append(",PourcentageNul_Votant=" + String.valueOf(PourcentageNul_Votant));
			sb.append(",Exprime=" + String.valueOf(Exprime));
			sb.append(",PourcentageExprime_Inscrit=" + String.valueOf(PourcentageExprime_Inscrit));
			sb.append(",PourcentageExprime_Votant=" + String.valueOf(PourcentageExprime_Votant));
			sb.append(",NumeroPanneau=" + String.valueOf(NumeroPanneau));
			sb.append(",Sexe=" + Sexe);
			sb.append(",Nom=" + Nom);
			sb.append(",Prenom=" + Prenom);
			sb.append(",Voix=" + String.valueOf(Voix));
			sb.append(",PourcentageVoix_Inscrit=" + String.valueOf(PourcentageVoix_Inscrit));
			sb.append(",PourcentageVoix_Exprime=" + String.valueOf(PourcentageVoix_Exprime));
			sb.append(",NumeroPanneau1=" + String.valueOf(NumeroPanneau1));
			sb.append(",Sexe1=" + Sexe1);
			sb.append(",Nom1=" + Nom1);
			sb.append(",Prenom1=" + Prenom1);
			sb.append(",Voix1=" + String.valueOf(Voix1));
			sb.append(",PourcentageVoix_Inscrit1=" + String.valueOf(PourcentageVoix_Inscrit1));
			sb.append(",PourcentageVoix_Exprime1=" + String.valueOf(PourcentageVoix_Exprime1));
			sb.append(",NumeroPanneau2=" + String.valueOf(NumeroPanneau2));
			sb.append(",Sexe2=" + Sexe2);
			sb.append(",Nom2=" + Nom2);
			sb.append(",Prenom2=" + Prenom2);
			sb.append(",Voix2=" + String.valueOf(Voix2));
			sb.append(",PourcentageVoix_Inscrit2=" + String.valueOf(PourcentageVoix_Inscrit2));
			sb.append(",PourcentageVoix_Exprime2=" + String.valueOf(PourcentageVoix_Exprime2));
			sb.append(",NumeroPanneau3=" + String.valueOf(NumeroPanneau3));
			sb.append(",Sexe3=" + Sexe3);
			sb.append(",Nom3=" + Nom3);
			sb.append(",Prenom3=" + Prenom3);
			sb.append(",Voix3=" + String.valueOf(Voix3));
			sb.append(",PourcentageVoix_Inscrit3=" + String.valueOf(PourcentageVoix_Inscrit3));
			sb.append(",PourcentageVoix_Exprime3=" + String.valueOf(PourcentageVoix_Exprime3));
			sb.append(",NumeroPanneau4=" + String.valueOf(NumeroPanneau4));
			sb.append(",Sexe4=" + Sexe4);
			sb.append(",Nom4=" + Nom4);
			sb.append(",Prenom4=" + Prenom4);
			sb.append(",Voix4=" + String.valueOf(Voix4));
			sb.append(",PourcentageVoix_Inscrit4=" + String.valueOf(PourcentageVoix_Inscrit4));
			sb.append(",PourcentageVoix_Exprime4=" + String.valueOf(PourcentageVoix_Exprime4));
			sb.append(",NumeroPanneau5=" + String.valueOf(NumeroPanneau5));
			sb.append(",Sexe5=" + Sexe5);
			sb.append(",Nom5=" + Nom5);
			sb.append(",Prenom5=" + Prenom5);
			sb.append(",Voix5=" + String.valueOf(Voix5));
			sb.append(",PourcentageVoix_Inscrit5=" + String.valueOf(PourcentageVoix_Inscrit5));
			sb.append(",PourcentageVoix_Exprime5=" + String.valueOf(PourcentageVoix_Exprime5));
			sb.append(",NumeroPanneau6=" + String.valueOf(NumeroPanneau6));
			sb.append(",Sexe6=" + Sexe6);
			sb.append(",Nom6=" + Nom6);
			sb.append(",Prenom6=" + Prenom6);
			sb.append(",Voix6=" + String.valueOf(Voix6));
			sb.append(",PourcentageVoix_Inscrit6=" + String.valueOf(PourcentageVoix_Inscrit6));
			sb.append(",PourcentageVoix_Exprime6=" + String.valueOf(PourcentageVoix_Exprime6));
			sb.append(",NumeroPanneau7=" + String.valueOf(NumeroPanneau7));
			sb.append(",Sexe7=" + Sexe7);
			sb.append(",Nom7=" + Nom7);
			sb.append(",Prenom7=" + Prenom7);
			sb.append(",Voix7=" + String.valueOf(Voix7));
			sb.append(",PourcentageVoix_Inscrit7=" + String.valueOf(PourcentageVoix_Inscrit7));
			sb.append(",PourcentageVoix_Exprime7=" + String.valueOf(PourcentageVoix_Exprime7));
			sb.append(",NumeroPanneau8=" + String.valueOf(NumeroPanneau8));
			sb.append(",Sexe8=" + Sexe8);
			sb.append(",Nom8=" + Nom8);
			sb.append(",Prenom8=" + Prenom8);
			sb.append(",Voix8=" + String.valueOf(Voix8));
			sb.append(",PourcentageVoix_Inscrit8=" + String.valueOf(PourcentageVoix_Inscrit8));
			sb.append(",PourcentageVoix_Exprime8=" + String.valueOf(PourcentageVoix_Exprime8));
			sb.append(",NumeroPanneau9=" + String.valueOf(NumeroPanneau9));
			sb.append(",Sexe9=" + Sexe9);
			sb.append(",Nom9=" + Nom9);
			sb.append(",Prenom9=" + Prenom9);
			sb.append(",Voix9=" + String.valueOf(Voix9));
			sb.append(",PourcentageVoix_Inscrit9=" + String.valueOf(PourcentageVoix_Inscrit9));
			sb.append(",PourcentageVoix_Exprime9=" + String.valueOf(PourcentageVoix_Exprime9));
			sb.append(",NumeroPanneau10=" + String.valueOf(NumeroPanneau10));
			sb.append(",Sexe10=" + Sexe10);
			sb.append(",Nom10=" + Nom10);
			sb.append(",Prenom10=" + Prenom10);
			sb.append(",Voix10=" + String.valueOf(Voix10));
			sb.append(",PourcentageVoix_Inscrit10=" + String.valueOf(PourcentageVoix_Inscrit10));
			sb.append(",PourcentageVoix_Exprime10=" + String.valueOf(PourcentageVoix_Exprime10));
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

	public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

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
				class FileOutputDelimitedUtil_tFileOutputDelimited_1 {
					public void putHeaderValue_0(java.io.Writer outtFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						outtFileOutputDelimited_1.write("Id");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodeDepartement");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LibelleDepartement");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodeCirconscription");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LibelleCirconscription");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Inscrit");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Abstention");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageAbstention_Inscrit");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Votant");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVotant_Inscrit");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VoteBlanc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageBlanc_Inscrit");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageBlanc_Votant");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VoteNul");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageNul_Inscrit");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageNul_Votant");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Exprime");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageExprime_Inscrit");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageExprime_Votant");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPanneau");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Sexe");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Nom");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prenom");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Voix");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Inscrit");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Exprime");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPanneau1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Sexe1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Nom1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prenom1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Voix1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Inscrit1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Exprime1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPanneau2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Sexe2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Nom2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prenom2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Voix2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Inscrit2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Exprime2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPanneau3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Sexe3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Nom3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prenom3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Voix3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Inscrit3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Exprime3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPanneau4");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Sexe4");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Nom4");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prenom4");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Voix4");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Inscrit4");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Exprime4");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPanneau5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Sexe5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Nom5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prenom5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Voix5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Inscrit5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Exprime5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPanneau6");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Sexe6");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Nom6");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prenom6");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Voix6");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Inscrit6");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Exprime6");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPanneau7");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Sexe7");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Nom7");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prenom7");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Voix7");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Inscrit7");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Exprime7");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPanneau8");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Sexe8");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Nom8");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prenom8");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Voix8");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Inscrit8");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Exprime8");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPanneau9");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Sexe9");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Nom9");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prenom9");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Voix9");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Inscrit9");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Exprime9");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPanneau10");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					}

					public void putHeaderValue_1(java.io.Writer outtFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						outtFileOutputDelimited_1.write("Sexe10");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Nom10");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prenom10");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Voix10");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Inscrit10");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PourcentageVoix_Exprime10");
					}

					public void putValue_0(final row4Struct row4, StringBuilder sb_tFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						sb_tFileOutputDelimited_1.append(row4.Id);
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CodeDepartement != null) {
							sb_tFileOutputDelimited_1.append(row4.CodeDepartement);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LibelleDepartement != null) {
							sb_tFileOutputDelimited_1.append(row4.LibelleDepartement);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CodeCirconscription != null) {
							sb_tFileOutputDelimited_1.append(row4.CodeCirconscription);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LibelleCirconscription != null) {
							sb_tFileOutputDelimited_1.append(row4.LibelleCirconscription);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Inscrit != null) {
							sb_tFileOutputDelimited_1.append(row4.Inscrit);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Abstention != null) {
							sb_tFileOutputDelimited_1.append(row4.Abstention);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageAbstention_Inscrit != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageAbstention_Inscrit.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Votant != null) {
							sb_tFileOutputDelimited_1.append(row4.Votant);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVotant_Inscrit != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVotant_Inscrit.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VoteBlanc != null) {
							sb_tFileOutputDelimited_1.append(row4.VoteBlanc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageBlanc_Inscrit != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageBlanc_Inscrit.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageBlanc_Votant != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageBlanc_Votant.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VoteNul != null) {
							sb_tFileOutputDelimited_1.append(row4.VoteNul);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageNul_Inscrit != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageNul_Inscrit.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageNul_Votant != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageNul_Votant.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Exprime != null) {
							sb_tFileOutputDelimited_1.append(row4.Exprime);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageExprime_Inscrit != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageExprime_Inscrit.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageExprime_Votant != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageExprime_Votant.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroPanneau != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPanneau);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Sexe != null) {
							sb_tFileOutputDelimited_1.append(row4.Sexe);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Nom != null) {
							sb_tFileOutputDelimited_1.append(row4.Nom);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Prenom != null) {
							sb_tFileOutputDelimited_1.append(row4.Prenom);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Voix != null) {
							sb_tFileOutputDelimited_1.append(row4.Voix);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Inscrit != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Inscrit.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Exprime != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Exprime.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroPanneau1 != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPanneau1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Sexe1 != null) {
							sb_tFileOutputDelimited_1.append(row4.Sexe1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Nom1 != null) {
							sb_tFileOutputDelimited_1.append(row4.Nom1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Prenom1 != null) {
							sb_tFileOutputDelimited_1.append(row4.Prenom1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Voix1 != null) {
							sb_tFileOutputDelimited_1.append(row4.Voix1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Inscrit1 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Inscrit1.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Exprime1 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Exprime1.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroPanneau2 != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPanneau2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Sexe2 != null) {
							sb_tFileOutputDelimited_1.append(row4.Sexe2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Nom2 != null) {
							sb_tFileOutputDelimited_1.append(row4.Nom2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Prenom2 != null) {
							sb_tFileOutputDelimited_1.append(row4.Prenom2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Voix2 != null) {
							sb_tFileOutputDelimited_1.append(row4.Voix2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Inscrit2 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Inscrit2.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Exprime2 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Exprime2.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroPanneau3 != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPanneau3);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Sexe3 != null) {
							sb_tFileOutputDelimited_1.append(row4.Sexe3);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Nom3 != null) {
							sb_tFileOutputDelimited_1.append(row4.Nom3);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Prenom3 != null) {
							sb_tFileOutputDelimited_1.append(row4.Prenom3);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Voix3 != null) {
							sb_tFileOutputDelimited_1.append(row4.Voix3);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Inscrit3 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Inscrit3.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Exprime3 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Exprime3.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroPanneau4 != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPanneau4);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Sexe4 != null) {
							sb_tFileOutputDelimited_1.append(row4.Sexe4);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Nom4 != null) {
							sb_tFileOutputDelimited_1.append(row4.Nom4);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Prenom4 != null) {
							sb_tFileOutputDelimited_1.append(row4.Prenom4);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Voix4 != null) {
							sb_tFileOutputDelimited_1.append(row4.Voix4);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Inscrit4 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Inscrit4.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Exprime4 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Exprime4.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroPanneau5 != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPanneau5);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Sexe5 != null) {
							sb_tFileOutputDelimited_1.append(row4.Sexe5);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Nom5 != null) {
							sb_tFileOutputDelimited_1.append(row4.Nom5);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Prenom5 != null) {
							sb_tFileOutputDelimited_1.append(row4.Prenom5);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Voix5 != null) {
							sb_tFileOutputDelimited_1.append(row4.Voix5);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Inscrit5 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Inscrit5.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Exprime5 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Exprime5.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroPanneau6 != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPanneau6);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Sexe6 != null) {
							sb_tFileOutputDelimited_1.append(row4.Sexe6);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Nom6 != null) {
							sb_tFileOutputDelimited_1.append(row4.Nom6);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Prenom6 != null) {
							sb_tFileOutputDelimited_1.append(row4.Prenom6);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Voix6 != null) {
							sb_tFileOutputDelimited_1.append(row4.Voix6);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Inscrit6 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Inscrit6.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Exprime6 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Exprime6.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroPanneau7 != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPanneau7);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Sexe7 != null) {
							sb_tFileOutputDelimited_1.append(row4.Sexe7);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Nom7 != null) {
							sb_tFileOutputDelimited_1.append(row4.Nom7);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Prenom7 != null) {
							sb_tFileOutputDelimited_1.append(row4.Prenom7);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Voix7 != null) {
							sb_tFileOutputDelimited_1.append(row4.Voix7);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Inscrit7 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Inscrit7.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Exprime7 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Exprime7.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroPanneau8 != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPanneau8);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Sexe8 != null) {
							sb_tFileOutputDelimited_1.append(row4.Sexe8);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Nom8 != null) {
							sb_tFileOutputDelimited_1.append(row4.Nom8);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Prenom8 != null) {
							sb_tFileOutputDelimited_1.append(row4.Prenom8);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Voix8 != null) {
							sb_tFileOutputDelimited_1.append(row4.Voix8);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Inscrit8 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Inscrit8.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Exprime8 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Exprime8.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroPanneau9 != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPanneau9);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Sexe9 != null) {
							sb_tFileOutputDelimited_1.append(row4.Sexe9);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Nom9 != null) {
							sb_tFileOutputDelimited_1.append(row4.Nom9);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Prenom9 != null) {
							sb_tFileOutputDelimited_1.append(row4.Prenom9);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Voix9 != null) {
							sb_tFileOutputDelimited_1.append(row4.Voix9);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Inscrit9 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Inscrit9.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Exprime9 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Exprime9.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroPanneau10 != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPanneau10);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					}

					public void putValue_1(final row4Struct row4, StringBuilder sb_tFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						if (row4.Sexe10 != null) {
							sb_tFileOutputDelimited_1.append(row4.Sexe10);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Nom10 != null) {
							sb_tFileOutputDelimited_1.append(row4.Nom10);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Prenom10 != null) {
							sb_tFileOutputDelimited_1.append(row4.Prenom10);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Voix10 != null) {
							sb_tFileOutputDelimited_1.append(row4.Voix10);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Inscrit10 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Inscrit10.toPlainString());
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PourcentageVoix_Exprime10 != null) {
							sb_tFileOutputDelimited_1.append(row4.PourcentageVoix_Exprime10.toPlainString());
						}
					}
				}
				FileOutputDelimitedUtil_tFileOutputDelimited_1 fileOutputDelimitedUtil_tFileOutputDelimited_1 = new FileOutputDelimitedUtil_tFileOutputDelimited_1();
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_output/VoteElection2017.csv"))
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
					fileOutputDelimitedUtil_tFileOutputDelimited_1.putHeaderValue_0(outtFileOutputDelimited_1,
							OUT_DELIM_tFileOutputDelimited_1);
					fileOutputDelimitedUtil_tFileOutputDelimited_1.putHeaderValue_1(outtFileOutputDelimited_1,
							OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
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

					int[] colLengths = new int[96];

					public void addRow(String[] row) {

						for (int i = 0; i < 96; i++) {
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
						for (k = 0; k < (totals + 95 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 95 - name.length() - k; i++) {
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

							sbformat.append("|%12$-");
							sbformat.append(colLengths[11]);
							sbformat.append("s");

							sbformat.append("|%13$-");
							sbformat.append(colLengths[12]);
							sbformat.append("s");

							sbformat.append("|%14$-");
							sbformat.append(colLengths[13]);
							sbformat.append("s");

							sbformat.append("|%15$-");
							sbformat.append(colLengths[14]);
							sbformat.append("s");

							sbformat.append("|%16$-");
							sbformat.append(colLengths[15]);
							sbformat.append("s");

							sbformat.append("|%17$-");
							sbformat.append(colLengths[16]);
							sbformat.append("s");

							sbformat.append("|%18$-");
							sbformat.append(colLengths[17]);
							sbformat.append("s");

							sbformat.append("|%19$-");
							sbformat.append(colLengths[18]);
							sbformat.append("s");

							sbformat.append("|%20$-");
							sbformat.append(colLengths[19]);
							sbformat.append("s");

							sbformat.append("|%21$-");
							sbformat.append(colLengths[20]);
							sbformat.append("s");

							sbformat.append("|%22$-");
							sbformat.append(colLengths[21]);
							sbformat.append("s");

							sbformat.append("|%23$-");
							sbformat.append(colLengths[22]);
							sbformat.append("s");

							sbformat.append("|%24$-");
							sbformat.append(colLengths[23]);
							sbformat.append("s");

							sbformat.append("|%25$-");
							sbformat.append(colLengths[24]);
							sbformat.append("s");

							sbformat.append("|%26$-");
							sbformat.append(colLengths[25]);
							sbformat.append("s");

							sbformat.append("|%27$-");
							sbformat.append(colLengths[26]);
							sbformat.append("s");

							sbformat.append("|%28$-");
							sbformat.append(colLengths[27]);
							sbformat.append("s");

							sbformat.append("|%29$-");
							sbformat.append(colLengths[28]);
							sbformat.append("s");

							sbformat.append("|%30$-");
							sbformat.append(colLengths[29]);
							sbformat.append("s");

							sbformat.append("|%31$-");
							sbformat.append(colLengths[30]);
							sbformat.append("s");

							sbformat.append("|%32$-");
							sbformat.append(colLengths[31]);
							sbformat.append("s");

							sbformat.append("|%33$-");
							sbformat.append(colLengths[32]);
							sbformat.append("s");

							sbformat.append("|%34$-");
							sbformat.append(colLengths[33]);
							sbformat.append("s");

							sbformat.append("|%35$-");
							sbformat.append(colLengths[34]);
							sbformat.append("s");

							sbformat.append("|%36$-");
							sbformat.append(colLengths[35]);
							sbformat.append("s");

							sbformat.append("|%37$-");
							sbformat.append(colLengths[36]);
							sbformat.append("s");

							sbformat.append("|%38$-");
							sbformat.append(colLengths[37]);
							sbformat.append("s");

							sbformat.append("|%39$-");
							sbformat.append(colLengths[38]);
							sbformat.append("s");

							sbformat.append("|%40$-");
							sbformat.append(colLengths[39]);
							sbformat.append("s");

							sbformat.append("|%41$-");
							sbformat.append(colLengths[40]);
							sbformat.append("s");

							sbformat.append("|%42$-");
							sbformat.append(colLengths[41]);
							sbformat.append("s");

							sbformat.append("|%43$-");
							sbformat.append(colLengths[42]);
							sbformat.append("s");

							sbformat.append("|%44$-");
							sbformat.append(colLengths[43]);
							sbformat.append("s");

							sbformat.append("|%45$-");
							sbformat.append(colLengths[44]);
							sbformat.append("s");

							sbformat.append("|%46$-");
							sbformat.append(colLengths[45]);
							sbformat.append("s");

							sbformat.append("|%47$-");
							sbformat.append(colLengths[46]);
							sbformat.append("s");

							sbformat.append("|%48$-");
							sbformat.append(colLengths[47]);
							sbformat.append("s");

							sbformat.append("|%49$-");
							sbformat.append(colLengths[48]);
							sbformat.append("s");

							sbformat.append("|%50$-");
							sbformat.append(colLengths[49]);
							sbformat.append("s");

							sbformat.append("|%51$-");
							sbformat.append(colLengths[50]);
							sbformat.append("s");

							sbformat.append("|%52$-");
							sbformat.append(colLengths[51]);
							sbformat.append("s");

							sbformat.append("|%53$-");
							sbformat.append(colLengths[52]);
							sbformat.append("s");

							sbformat.append("|%54$-");
							sbformat.append(colLengths[53]);
							sbformat.append("s");

							sbformat.append("|%55$-");
							sbformat.append(colLengths[54]);
							sbformat.append("s");

							sbformat.append("|%56$-");
							sbformat.append(colLengths[55]);
							sbformat.append("s");

							sbformat.append("|%57$-");
							sbformat.append(colLengths[56]);
							sbformat.append("s");

							sbformat.append("|%58$-");
							sbformat.append(colLengths[57]);
							sbformat.append("s");

							sbformat.append("|%59$-");
							sbformat.append(colLengths[58]);
							sbformat.append("s");

							sbformat.append("|%60$-");
							sbformat.append(colLengths[59]);
							sbformat.append("s");

							sbformat.append("|%61$-");
							sbformat.append(colLengths[60]);
							sbformat.append("s");

							sbformat.append("|%62$-");
							sbformat.append(colLengths[61]);
							sbformat.append("s");

							sbformat.append("|%63$-");
							sbformat.append(colLengths[62]);
							sbformat.append("s");

							sbformat.append("|%64$-");
							sbformat.append(colLengths[63]);
							sbformat.append("s");

							sbformat.append("|%65$-");
							sbformat.append(colLengths[64]);
							sbformat.append("s");

							sbformat.append("|%66$-");
							sbformat.append(colLengths[65]);
							sbformat.append("s");

							sbformat.append("|%67$-");
							sbformat.append(colLengths[66]);
							sbformat.append("s");

							sbformat.append("|%68$-");
							sbformat.append(colLengths[67]);
							sbformat.append("s");

							sbformat.append("|%69$-");
							sbformat.append(colLengths[68]);
							sbformat.append("s");

							sbformat.append("|%70$-");
							sbformat.append(colLengths[69]);
							sbformat.append("s");

							sbformat.append("|%71$-");
							sbformat.append(colLengths[70]);
							sbformat.append("s");

							sbformat.append("|%72$-");
							sbformat.append(colLengths[71]);
							sbformat.append("s");

							sbformat.append("|%73$-");
							sbformat.append(colLengths[72]);
							sbformat.append("s");

							sbformat.append("|%74$-");
							sbformat.append(colLengths[73]);
							sbformat.append("s");

							sbformat.append("|%75$-");
							sbformat.append(colLengths[74]);
							sbformat.append("s");

							sbformat.append("|%76$-");
							sbformat.append(colLengths[75]);
							sbformat.append("s");

							sbformat.append("|%77$-");
							sbformat.append(colLengths[76]);
							sbformat.append("s");

							sbformat.append("|%78$-");
							sbformat.append(colLengths[77]);
							sbformat.append("s");

							sbformat.append("|%79$-");
							sbformat.append(colLengths[78]);
							sbformat.append("s");

							sbformat.append("|%80$-");
							sbformat.append(colLengths[79]);
							sbformat.append("s");

							sbformat.append("|%81$-");
							sbformat.append(colLengths[80]);
							sbformat.append("s");

							sbformat.append("|%82$-");
							sbformat.append(colLengths[81]);
							sbformat.append("s");

							sbformat.append("|%83$-");
							sbformat.append(colLengths[82]);
							sbformat.append("s");

							sbformat.append("|%84$-");
							sbformat.append(colLengths[83]);
							sbformat.append("s");

							sbformat.append("|%85$-");
							sbformat.append(colLengths[84]);
							sbformat.append("s");

							sbformat.append("|%86$-");
							sbformat.append(colLengths[85]);
							sbformat.append("s");

							sbformat.append("|%87$-");
							sbformat.append(colLengths[86]);
							sbformat.append("s");

							sbformat.append("|%88$-");
							sbformat.append(colLengths[87]);
							sbformat.append("s");

							sbformat.append("|%89$-");
							sbformat.append(colLengths[88]);
							sbformat.append("s");

							sbformat.append("|%90$-");
							sbformat.append(colLengths[89]);
							sbformat.append("s");

							sbformat.append("|%91$-");
							sbformat.append(colLengths[90]);
							sbformat.append("s");

							sbformat.append("|%92$-");
							sbformat.append(colLengths[91]);
							sbformat.append("s");

							sbformat.append("|%93$-");
							sbformat.append(colLengths[92]);
							sbformat.append("s");

							sbformat.append("|%94$-");
							sbformat.append(colLengths[93]);
							sbformat.append("s");

							sbformat.append("|%95$-");
							sbformat.append(colLengths[94]);
							sbformat.append("s");

							sbformat.append("|%96$-");
							sbformat.append(colLengths[95]);
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
						for (int i = 0; i < colLengths[10] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[11] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[12] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[13] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[14] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[15] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[16] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[17] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[18] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[19] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[20] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[21] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[22] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[23] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[24] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[25] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[26] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[27] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[28] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[29] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[30] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[31] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[32] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[33] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[34] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[35] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[36] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[37] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[38] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[39] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[40] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[41] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[42] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[43] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[44] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[45] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[46] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[47] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[48] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[49] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[50] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[51] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[52] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[53] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[54] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[55] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[56] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[57] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[58] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[59] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[60] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[61] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[62] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[63] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[64] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[65] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[66] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[67] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[68] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[69] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[70] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[71] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[72] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[73] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[74] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[75] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[76] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[77] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[78] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[79] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[80] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[81] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[82] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[83] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[84] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[85] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[86] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[87] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[88] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[89] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[90] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[91] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[92] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[93] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[94] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[95] - fillChars[1].length() + 1; i++) {
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
				util_tLogRow_1.addRow(new String[] { "Id", "CodeDepartement", "LibelleDepartement",
						"CodeCirconscription", "LibelleCirconscription", "Inscrit", "Abstention",
						"PourcentageAbstention_Inscrit", "Votant", "PourcentageVotant_Inscrit", "VoteBlanc",
						"PourcentageBlanc_Inscrit", "PourcentageBlanc_Votant", "VoteNul", "PourcentageNul_Inscrit",
						"PourcentageNul_Votant", "Exprime", "PourcentageExprime_Inscrit", "PourcentageExprime_Votant",
						"NumeroPanneau", "Sexe", "Nom", "Prenom", "Voix", "PourcentageVoix_Inscrit",
						"PourcentageVoix_Exprime", "NumeroPanneau1", "Sexe1", "Nom1", "Prenom1", "Voix1",
						"PourcentageVoix_Inscrit1", "PourcentageVoix_Exprime1", "NumeroPanneau2", "Sexe2", "Nom2",
						"Prenom2", "Voix2", "PourcentageVoix_Inscrit2", "PourcentageVoix_Exprime2", "NumeroPanneau3",
						"Sexe3", "Nom3", "Prenom3", "Voix3", "PourcentageVoix_Inscrit3", "PourcentageVoix_Exprime3",
						"NumeroPanneau4", "Sexe4", "Nom4", "Prenom4", "Voix4", "PourcentageVoix_Inscrit4",
						"PourcentageVoix_Exprime4", "NumeroPanneau5", "Sexe5", "Nom5", "Prenom5", "Voix5",
						"PourcentageVoix_Inscrit5", "PourcentageVoix_Exprime5", "NumeroPanneau6", "Sexe6", "Nom6",
						"Prenom6", "Voix6", "PourcentageVoix_Inscrit6", "PourcentageVoix_Exprime6", "NumeroPanneau7",
						"Sexe7", "Nom7", "Prenom7", "Voix7", "PourcentageVoix_Inscrit7", "PourcentageVoix_Exprime7",
						"NumeroPanneau8", "Sexe8", "Nom8", "Prenom8", "Voix8", "PourcentageVoix_Inscrit8",
						"PourcentageVoix_Exprime8", "NumeroPanneau9", "Sexe9", "Nom9", "Prenom9", "Voix9",
						"PourcentageVoix_Inscrit9", "PourcentageVoix_Exprime9", "NumeroPanneau10", "Sexe10", "Nom10",
						"Prenom10", "Voix10", "PourcentageVoix_Inscrit10", "PourcentageVoix_Exprime10", });
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
					String Candidat;
					String Candidat1;
					String Candidat2;
					String Candidat3;
					String Candidat4;
					String Candidat5;
					String Candidat6;
					String Candidat7;
					String Candidat8;
					String Candidat9;
					String Candidat10;
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
				 * [tFileInputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_1", false);
				start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_1";

				int tos_count_tFileInputExcel_1 = 0;

				class RegexUtil_tFileInputExcel_1 {

					public java.util.List<jxl.Sheet> getSheets(jxl.Workbook workbook, String oneSheetName,
							boolean useRegex) {

						java.util.List<jxl.Sheet> list = new java.util.ArrayList<jxl.Sheet>();

						if (useRegex) {// this part process the regex issue

							jxl.Sheet[] sheets = workbook.getSheets();
							java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
							for (int i = 0; i < sheets.length; i++) {
								String sheetName = sheets[i].getName();
								java.util.regex.Matcher matcher = pattern.matcher(sheetName);
								if (matcher.matches()) {
									jxl.Sheet sheet = workbook.getSheet(sheetName);
									if (sheet != null) {
										list.add(sheet);
									}
								}
							}

						} else {
							jxl.Sheet sheet = workbook.getSheet(oneSheetName);
							if (sheet != null) {
								list.add(sheet);
							}

						}

						return list;
					}

					public java.util.List<jxl.Sheet> getSheets(jxl.Workbook workbook, int index, boolean useRegex) {
						java.util.List<jxl.Sheet> list = new java.util.ArrayList<jxl.Sheet>();
						jxl.Sheet sheet = workbook.getSheet(index);
						if (sheet != null) {
							list.add(sheet);
						}
						return list;
					}

				}

				RegexUtil_tFileInputExcel_1 regexUtil_tFileInputExcel_1 = new RegexUtil_tFileInputExcel_1();
				final jxl.WorkbookSettings workbookSettings_tFileInputExcel_1 = new jxl.WorkbookSettings();
				workbookSettings_tFileInputExcel_1.setDrawingsDisabled(true);
				workbookSettings_tFileInputExcel_1.setEncoding("ISO-8859-15");

				Object source_tFileInputExcel_1 = "C:/Program Files (x86)/TOS_DI-8.0.1/studio/workspace/DATAMSPR/_input/Presidentielle_2017_Resultats_Tour_1_c.xls";
				final jxl.Workbook workbook_tFileInputExcel_1;

				java.io.InputStream toClose_tFileInputExcel_1 = null;
				java.io.BufferedInputStream buffIStreamtFileInputExcel_1 = null;
				try {
					if (source_tFileInputExcel_1 instanceof java.io.InputStream) {
						toClose_tFileInputExcel_1 = (java.io.InputStream) source_tFileInputExcel_1;
						buffIStreamtFileInputExcel_1 = new java.io.BufferedInputStream(toClose_tFileInputExcel_1);
						workbook_tFileInputExcel_1 = jxl.Workbook.getWorkbook(buffIStreamtFileInputExcel_1,
								workbookSettings_tFileInputExcel_1);
					} else if (source_tFileInputExcel_1 instanceof String) {
						toClose_tFileInputExcel_1 = new java.io.FileInputStream(source_tFileInputExcel_1.toString());
						buffIStreamtFileInputExcel_1 = new java.io.BufferedInputStream(toClose_tFileInputExcel_1);
						workbook_tFileInputExcel_1 = jxl.Workbook.getWorkbook(buffIStreamtFileInputExcel_1,
								workbookSettings_tFileInputExcel_1);
					} else {
						workbook_tFileInputExcel_1 = null;
						throw new java.lang.Exception(
								"The data source should be specified as Inputstream or File Path!");
					}
				} finally {
					try {
						if (buffIStreamtFileInputExcel_1 != null) {
							buffIStreamtFileInputExcel_1.close();
						}
					} catch (Exception e) {
						globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
					}
				}
				try {
					java.util.List<jxl.Sheet> sheetList_tFileInputExcel_1 = new java.util.ArrayList<jxl.Sheet>();
					sheetList_tFileInputExcel_1.addAll(regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1,
							"Circo. Leg. Tour 1", false));
					if (sheetList_tFileInputExcel_1.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<jxl.Sheet> sheet_FilterNullList_tFileInputExcel_1 = new java.util.ArrayList<jxl.Sheet>();
					for (jxl.Sheet sheet_FilterNull_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
						if (sheet_FilterNull_tFileInputExcel_1.getRows() > 0) {
							sheet_FilterNullList_tFileInputExcel_1.add(sheet_FilterNull_tFileInputExcel_1);
						}
					}
					sheetList_tFileInputExcel_1 = sheet_FilterNullList_tFileInputExcel_1;
					if (sheetList_tFileInputExcel_1.size() > 0) {
						int nb_line_tFileInputExcel_1 = 0;

						int begin_line_tFileInputExcel_1 = 1;

						int footer_input_tFileInputExcel_1 = 0;

						int end_line_tFileInputExcel_1 = 0;
						for (jxl.Sheet sheet_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
							end_line_tFileInputExcel_1 += sheet_tFileInputExcel_1.getRows();
						}
						end_line_tFileInputExcel_1 -= footer_input_tFileInputExcel_1;
						int limit_tFileInputExcel_1 = -1;
						int start_column_tFileInputExcel_1 = 1 - 1;
						int end_column_tFileInputExcel_1 = sheetList_tFileInputExcel_1.get(0).getColumns();
						jxl.Cell[] row_tFileInputExcel_1 = null;
						jxl.Sheet sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1.get(0);
						int rowCount_tFileInputExcel_1 = 0;
						int sheetIndex_tFileInputExcel_1 = 0;
						int currentRows_tFileInputExcel_1 = sheetList_tFileInputExcel_1.get(0).getRows();

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat(
								"#.####################################");
						char separatorChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols()
								.getDecimalSeparator();

						for (int i_tFileInputExcel_1 = begin_line_tFileInputExcel_1; i_tFileInputExcel_1 < end_line_tFileInputExcel_1; i_tFileInputExcel_1++) {

							int emptyColumnCount_tFileInputExcel_1 = 0;

							if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
								break;
							}

							while (i_tFileInputExcel_1 >= rowCount_tFileInputExcel_1 + currentRows_tFileInputExcel_1) {
								rowCount_tFileInputExcel_1 += currentRows_tFileInputExcel_1;
								sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
										.get(++sheetIndex_tFileInputExcel_1);
								currentRows_tFileInputExcel_1 = sheet_tFileInputExcel_1.getRows();
							}
							if (rowCount_tFileInputExcel_1 <= i_tFileInputExcel_1) {
								row_tFileInputExcel_1 = sheet_tFileInputExcel_1
										.getRow(i_tFileInputExcel_1 - rowCount_tFileInputExcel_1);
							}
							globalMap.put("tFileInputExcel_1_CURRENT_SHEET", sheet_tFileInputExcel_1.getName());
							row1 = null;
							int tempRowLength_tFileInputExcel_1 = 95;

							int columnIndex_tFileInputExcel_1 = 0;

//
//end%>

							String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];
							int actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 > row_tFileInputExcel_1.length
									? row_tFileInputExcel_1.length
									: end_column_tFileInputExcel_1;

							java.util.TimeZone zone_tFileInputExcel_1 = java.util.TimeZone.getTimeZone("GMT");
							java.text.SimpleDateFormat sdf_tFileInputExcel_1 = new java.text.SimpleDateFormat(
									"dd-MM-yyyy");
							sdf_tFileInputExcel_1.setTimeZone(zone_tFileInputExcel_1);

							for (int i = 0; i < tempRowLength_tFileInputExcel_1; i++) {

								if (i + start_column_tFileInputExcel_1 < actual_end_column_tFileInputExcel_1) {

									jxl.Cell cell_tFileInputExcel_1 = row_tFileInputExcel_1[i
											+ start_column_tFileInputExcel_1];
									temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1.getContents();

								} else {
									temp_row_tFileInputExcel_1[i] = "";
								}
							}

							boolean whetherReject_tFileInputExcel_1 = false;
							row1 = new row1Struct();
							int curColNum_tFileInputExcel_1 = -1;
							String curColName_tFileInputExcel_1 = "";
							try {
								columnIndex_tFileInputExcel_1 = 0;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "CodeDepartement";
									row1.CodeDepartement = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1]
											.trim();
								} else {
									row1.CodeDepartement = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 1;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "LibelleDepartement";
									row1.LibelleDepartement = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1]
											.trim();
								} else {
									row1.LibelleDepartement = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 2;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "CodeCirconscription";
									row1.CodeCirconscription = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1]
											.trim();
								} else {
									row1.CodeCirconscription = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 3;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "LibelleCirconscription";
									row1.LibelleCirconscription = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1]
											.trim();
								} else {
									row1.LibelleCirconscription = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 4;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Inscrit";
									row1.Inscrit = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Inscrit = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 5;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Abstention";
									row1.Abstention = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Abstention = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 6;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageAbstention_Inscrit";
									row1.PourcentageAbstention_Inscrit = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageAbstention_Inscrit = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 7;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Votant";
									row1.Votant = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Votant = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 8;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVotant_Inscrit";
									row1.PourcentageVotant_Inscrit = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVotant_Inscrit = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 9;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "VoteBlanc";
									row1.VoteBlanc = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.VoteBlanc = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 10;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageBlanc_Inscrit";
									row1.PourcentageBlanc_Inscrit = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageBlanc_Inscrit = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 11;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageBlanc_Votant";
									row1.PourcentageBlanc_Votant = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageBlanc_Votant = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 12;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "VoteNul";
									row1.VoteNul = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.VoteNul = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 13;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageNul_Inscrit";
									row1.PourcentageNul_Inscrit = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageNul_Inscrit = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 14;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageNul_Votant";
									row1.PourcentageNul_Votant = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageNul_Votant = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 15;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Exprime";
									row1.Exprime = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Exprime = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 16;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageExprime_Inscrit";
									row1.PourcentageExprime_Inscrit = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageExprime_Inscrit = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 17;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageExprime_Votant";
									row1.PourcentageExprime_Votant = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageExprime_Votant = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 18;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "NumeroPanneau";
									row1.NumeroPanneau = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.NumeroPanneau = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 19;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sexe";
									row1.Sexe = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Sexe = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 20;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom";
									row1.Nom = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Nom = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 21;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prenom";
									row1.Prenom = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Prenom = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 22;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Voix";
									row1.Voix = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Voix = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 23;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Inscrit";
									row1.PourcentageVoix_Inscrit = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Inscrit = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 24;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Exprime";
									row1.PourcentageVoix_Exprime = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Exprime = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 25;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "NumeroPanneau1";
									row1.NumeroPanneau1 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.NumeroPanneau1 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 26;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sexe1";
									row1.Sexe1 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Sexe1 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 27;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom1";
									row1.Nom1 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Nom1 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 28;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prenom1";
									row1.Prenom1 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Prenom1 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 29;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Voix1";
									row1.Voix1 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Voix1 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 30;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Inscrit1";
									row1.PourcentageVoix_Inscrit1 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Inscrit1 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 31;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Exprime1";
									row1.PourcentageVoix_Exprime1 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Exprime1 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 32;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "NumeroPanneau2";
									row1.NumeroPanneau2 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.NumeroPanneau2 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 33;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sexe2";
									row1.Sexe2 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Sexe2 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 34;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom2";
									row1.Nom2 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Nom2 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 35;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prenom2";
									row1.Prenom2 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Prenom2 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 36;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Voix2";
									row1.Voix2 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Voix2 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 37;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Inscrit2";
									row1.PourcentageVoix_Inscrit2 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Inscrit2 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 38;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Exprime2";
									row1.PourcentageVoix_Exprime2 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Exprime2 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 39;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "NumeroPanneau3";
									row1.NumeroPanneau3 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.NumeroPanneau3 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 40;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sexe3";
									row1.Sexe3 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Sexe3 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 41;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom3";
									row1.Nom3 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Nom3 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 42;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prenom3";
									row1.Prenom3 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Prenom3 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 43;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Voix3";
									row1.Voix3 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Voix3 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 44;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Inscrit3";
									row1.PourcentageVoix_Inscrit3 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Inscrit3 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 45;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Exprime3";
									row1.PourcentageVoix_Exprime3 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Exprime3 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 46;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "NumeroPanneau4";
									row1.NumeroPanneau4 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.NumeroPanneau4 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 47;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sexe4";
									row1.Sexe4 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Sexe4 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 48;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom4";
									row1.Nom4 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Nom4 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 49;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prenom4";
									row1.Prenom4 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Prenom4 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 50;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Voix4";
									row1.Voix4 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Voix4 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 51;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Inscrit4";
									row1.PourcentageVoix_Inscrit4 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Inscrit4 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 52;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Exprime4";
									row1.PourcentageVoix_Exprime4 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Exprime4 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 53;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "NumeroPanneau5";
									row1.NumeroPanneau5 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.NumeroPanneau5 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 54;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sexe5";
									row1.Sexe5 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Sexe5 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 55;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom5";
									row1.Nom5 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Nom5 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 56;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prenom5";
									row1.Prenom5 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Prenom5 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 57;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Voix5";
									row1.Voix5 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Voix5 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 58;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Inscrit5";
									row1.PourcentageVoix_Inscrit5 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Inscrit5 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 59;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Exprime5";
									row1.PourcentageVoix_Exprime5 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Exprime5 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 60;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "NumeroPanneau6";
									row1.NumeroPanneau6 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.NumeroPanneau6 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 61;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sexe6";
									row1.Sexe6 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Sexe6 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 62;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom6";
									row1.Nom6 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Nom6 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 63;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prenom6";
									row1.Prenom6 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Prenom6 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 64;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Voix6";
									row1.Voix6 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Voix6 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 65;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Inscrit6";
									row1.PourcentageVoix_Inscrit6 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Inscrit6 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 66;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Exprime6";
									row1.PourcentageVoix_Exprime6 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Exprime6 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 67;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "NumeroPanneau7";
									row1.NumeroPanneau7 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.NumeroPanneau7 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 68;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sexe7";
									row1.Sexe7 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Sexe7 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 69;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom7";
									row1.Nom7 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Nom7 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 70;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prenom7";
									row1.Prenom7 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Prenom7 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 71;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Voix7";
									row1.Voix7 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Voix7 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 72;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Inscrit7";
									row1.PourcentageVoix_Inscrit7 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Inscrit7 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 73;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Exprime7";
									row1.PourcentageVoix_Exprime7 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Exprime7 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 74;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "NumeroPanneau8";
									row1.NumeroPanneau8 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.NumeroPanneau8 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 75;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sexe8";
									row1.Sexe8 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Sexe8 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 76;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom8";
									row1.Nom8 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Nom8 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 77;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prenom8";
									row1.Prenom8 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Prenom8 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 78;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Voix8";
									row1.Voix8 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Voix8 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 79;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Inscrit8";
									row1.PourcentageVoix_Inscrit8 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Inscrit8 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 80;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Exprime8";
									row1.PourcentageVoix_Exprime8 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Exprime8 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 81;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "NumeroPanneau9";
									row1.NumeroPanneau9 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.NumeroPanneau9 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 82;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sexe9";
									row1.Sexe9 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Sexe9 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 83;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom9";
									row1.Nom9 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Nom9 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 84;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prenom9";
									row1.Prenom9 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Prenom9 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 85;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Voix9";
									row1.Voix9 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Voix9 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 86;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Inscrit9";
									row1.PourcentageVoix_Inscrit9 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Inscrit9 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 87;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Exprime9";
									row1.PourcentageVoix_Exprime9 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Exprime9 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 88;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "NumeroPanneau10";
									row1.NumeroPanneau10 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.NumeroPanneau10 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 89;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sexe10";
									row1.Sexe10 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Sexe10 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 90;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom10";
									row1.Nom10 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Nom10 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 91;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prenom10";
									row1.Prenom10 = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim();
								} else {
									row1.Prenom10 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 92;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Voix10";
									row1.Voix10 = ParserUtils.parseTo_Integer(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.Voix10 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 93;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Inscrit10";
									row1.PourcentageVoix_Inscrit10 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Inscrit10 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 94;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim().length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PourcentageVoix_Exprime10";
									row1.PourcentageVoix_Exprime10 = ParserUtils.parseTo_BigDecimal(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].trim());
								} else {
									row1.PourcentageVoix_Exprime10 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}

								nb_line_tFileInputExcel_1++;

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_1 = true;
								System.err.println(e.getMessage());
								row1 = null;
							}

							/**
							 * [tFileInputExcel_1 begin ] stop
							 */

							/**
							 * [tFileInputExcel_1 main ] start
							 */

							currentComponent = "tFileInputExcel_1";

							tos_count_tFileInputExcel_1++;

							/**
							 * [tFileInputExcel_1 main ] stop
							 */

							/**
							 * [tFileInputExcel_1 process_data_begin ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_begin ] stop
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
									Var.Candidat = row1.NumeroPanneau + ";" + row1.Sexe + ";" + row1.Nom + ";"
											+ row1.Prenom + ";" + row1.Voix + ";" + row1.PourcentageVoix_Inscrit + ";"
											+ row1.PourcentageVoix_Exprime;
									Var.Candidat1 = row1.NumeroPanneau1 + ";" + row1.Sexe1 + ";" + row1.Nom1 + ";"
											+ row1.Prenom1 + ";" + row1.Voix1 + ";" + row1.PourcentageVoix_Inscrit1
											+ ";" + row1.PourcentageVoix_Exprime1;
									Var.Candidat2 = row1.NumeroPanneau2 + ";" + row1.Sexe2 + ";" + row1.Nom2 + ";"
											+ row1.Prenom2 + ";" + row1.Voix2 + ";" + row1.PourcentageVoix_Inscrit2
											+ ";" + row1.PourcentageVoix_Exprime2;
									Var.Candidat3 = row1.NumeroPanneau3 + ";" + row1.Sexe3 + ";" + row1.Nom3 + ";"
											+ row1.Prenom3 + ";" + row1.Voix3 + ";" + row1.PourcentageVoix_Inscrit3
											+ ";" + row1.PourcentageVoix_Exprime3;
									Var.Candidat4 = row1.NumeroPanneau4 + ";" + row1.Sexe4 + ";" + row1.Nom4 + ";"
											+ row1.Prenom4 + ";" + row1.Voix4 + ";" + row1.PourcentageVoix_Inscrit4
											+ ";" + row1.PourcentageVoix_Exprime4;
									Var.Candidat5 = row1.NumeroPanneau5 + ";" + row1.Sexe5 + ";" + row1.Nom5 + ";"
											+ row1.Prenom5 + ";" + row1.Voix5 + ";" + row1.PourcentageVoix_Inscrit5
											+ ";" + row1.PourcentageVoix_Exprime5;
									Var.Candidat6 = row1.NumeroPanneau6 + ";" + row1.Sexe6 + ";" + row1.Nom6 + ";"
											+ row1.Prenom6 + ";" + row1.Voix6 + ";" + row1.PourcentageVoix_Inscrit6
											+ ";" + row1.PourcentageVoix_Exprime6;
									Var.Candidat7 = row1.NumeroPanneau7 + ";" + row1.Sexe7 + ";" + row1.Nom7 + ";"
											+ row1.Prenom7 + ";" + row1.Voix7 + ";" + row1.PourcentageVoix_Inscrit7
											+ ";" + row1.PourcentageVoix_Exprime7;
									Var.Candidat8 = row1.NumeroPanneau8 + ";" + row1.Sexe8 + ";" + row1.Nom8 + ";"
											+ row1.Prenom8 + ";" + row1.Voix8 + ";" + row1.PourcentageVoix_Inscrit8
											+ ";" + row1.PourcentageVoix_Exprime8;
									Var.Candidat9 = row1.NumeroPanneau9 + ";" + row1.Sexe9 + ";" + row1.Nom9 + ";"
											+ row1.Prenom9 + ";" + row1.Voix9 + ";" + row1.PourcentageVoix_Inscrit9
											+ ";" + row1.PourcentageVoix_Exprime9;
									Var.Candidat10 = row1.NumeroPanneau10 + ";" + row1.Sexe10 + ";" + row1.Nom10 + ";"
											+ row1.Prenom10 + ";" + row1.Voix10 + ";" + row1.PourcentageVoix_Inscrit10
											+ ";" + row1.PourcentageVoix_Exprime10;// ###############################
									// ###############################
									// # Output tables

									out1 = null;

// # Output table : 'out1'
									out1_tmp.Id = Var.Id;
									out1_tmp.CodeDepartement = row1.CodeDepartement;
									out1_tmp.LibelleDepartement = row1.LibelleDepartement;
									out1_tmp.CodeCirconscription = row1.CodeCirconscription;
									out1_tmp.LibelleCirconscription = row1.LibelleCirconscription;
									out1_tmp.Inscrit = row1.Inscrit;
									out1_tmp.Abstention = row1.Abstention;
									out1_tmp.PourcentageAbstention_Inscrit = row1.PourcentageAbstention_Inscrit;
									out1_tmp.Votant = row1.Votant;
									out1_tmp.PourcentageVotant_Inscrit = row1.PourcentageVotant_Inscrit;
									out1_tmp.VoteBlanc = row1.VoteBlanc;
									out1_tmp.PourcentageBlanc_Inscrit = row1.PourcentageBlanc_Inscrit;
									out1_tmp.PourcentageBlanc_Votant = row1.PourcentageBlanc_Votant;
									out1_tmp.VoteNul = row1.VoteNul;
									out1_tmp.PourcentageNul_Inscrit = row1.PourcentageNul_Inscrit;
									out1_tmp.PourcentageNul_Votant = row1.PourcentageNul_Votant;
									out1_tmp.Exprime = row1.Exprime;
									out1_tmp.PourcentageExprime_Inscrit = row1.PourcentageExprime_Inscrit;
									out1_tmp.PourcentageExprime_Votant = row1.PourcentageExprime_Votant;
									out1_tmp.NumeroPanneau = row1.NumeroPanneau;
									out1_tmp.Sexe = row1.Sexe;
									out1_tmp.Nom = row1.Nom;
									out1_tmp.Prenom = row1.Prenom;
									out1_tmp.Voix = row1.Voix;
									out1_tmp.PourcentageVoix_Inscrit = row1.PourcentageVoix_Inscrit;
									out1_tmp.PourcentageVoix_Exprime = row1.PourcentageVoix_Exprime;
									out1_tmp.NumeroPanneau1 = row1.NumeroPanneau1;
									out1_tmp.Sexe1 = row1.Sexe1;
									out1_tmp.Nom1 = row1.Nom1;
									out1_tmp.Prenom1 = row1.Prenom1;
									out1_tmp.Voix1 = row1.Voix1;
									out1_tmp.PourcentageVoix_Inscrit1 = row1.PourcentageVoix_Inscrit1;
									out1_tmp.PourcentageVoix_Exprime1 = row1.PourcentageVoix_Exprime1;
									out1_tmp.NumeroPanneau2 = row1.NumeroPanneau2;
									out1_tmp.Sexe2 = row1.Sexe2;
									out1_tmp.Nom2 = row1.Nom2;
									out1_tmp.Prenom2 = row1.Prenom2;
									out1_tmp.Voix2 = row1.Voix2;
									out1_tmp.PourcentageVoix_Inscrit2 = row1.PourcentageVoix_Inscrit2;
									out1_tmp.PourcentageVoix_Exprime2 = row1.PourcentageVoix_Exprime2;
									out1_tmp.NumeroPanneau3 = row1.NumeroPanneau3;
									out1_tmp.Sexe3 = row1.Sexe3;
									out1_tmp.Nom3 = row1.Nom3;
									out1_tmp.Prenom3 = row1.Prenom3;
									out1_tmp.Voix3 = row1.Voix3;
									out1_tmp.PourcentageVoix_Inscrit3 = row1.PourcentageVoix_Inscrit3;
									out1_tmp.PourcentageVoix_Exprime3 = row1.PourcentageVoix_Exprime3;
									out1_tmp.NumeroPanneau4 = row1.NumeroPanneau4;
									out1_tmp.Sexe4 = row1.Sexe4;
									out1_tmp.Nom4 = row1.Nom4;
									out1_tmp.Prenom4 = row1.Prenom4;
									out1_tmp.Voix4 = row1.Voix4;
									out1_tmp.PourcentageVoix_Inscrit4 = row1.PourcentageVoix_Inscrit4;
									out1_tmp.PourcentageVoix_Exprime4 = row1.PourcentageVoix_Exprime4;
									out1_tmp.NumeroPanneau5 = row1.NumeroPanneau5;
									out1_tmp.Sexe5 = row1.Sexe5;
									out1_tmp.Nom5 = row1.Nom5;
									out1_tmp.Prenom5 = row1.Prenom5;
									out1_tmp.Voix5 = row1.Voix5;
									out1_tmp.PourcentageVoix_Inscrit5 = row1.PourcentageVoix_Inscrit5;
									out1_tmp.PourcentageVoix_Exprime5 = row1.PourcentageVoix_Exprime5;
									out1_tmp.NumeroPanneau6 = row1.NumeroPanneau6;
									out1_tmp.Sexe6 = row1.Sexe6;
									out1_tmp.Nom6 = row1.Nom6;
									out1_tmp.Prenom6 = row1.Prenom6;
									out1_tmp.Voix6 = row1.Voix6;
									out1_tmp.PourcentageVoix_Inscrit6 = row1.PourcentageVoix_Inscrit6;
									out1_tmp.PourcentageVoix_Exprime6 = row1.PourcentageVoix_Exprime6;
									out1_tmp.NumeroPanneau7 = row1.NumeroPanneau7;
									out1_tmp.Sexe7 = row1.Sexe7;
									out1_tmp.Nom7 = row1.Nom7;
									out1_tmp.Prenom7 = row1.Prenom7;
									out1_tmp.Voix7 = row1.Voix7;
									out1_tmp.PourcentageVoix_Inscrit7 = row1.PourcentageVoix_Inscrit7;
									out1_tmp.PourcentageVoix_Exprime7 = row1.PourcentageVoix_Exprime7;
									out1_tmp.NumeroPanneau8 = row1.NumeroPanneau8;
									out1_tmp.Sexe8 = row1.Sexe8;
									out1_tmp.Nom8 = row1.Nom8;
									out1_tmp.Prenom8 = row1.Prenom8;
									out1_tmp.Voix8 = row1.Voix8;
									out1_tmp.PourcentageVoix_Inscrit8 = row1.PourcentageVoix_Inscrit8;
									out1_tmp.PourcentageVoix_Exprime8 = row1.PourcentageVoix_Exprime8;
									out1_tmp.NumeroPanneau9 = row1.NumeroPanneau9;
									out1_tmp.Sexe9 = row1.Sexe9;
									out1_tmp.Nom9 = row1.Nom9;
									out1_tmp.Prenom9 = row1.Prenom9;
									out1_tmp.Voix9 = row1.Voix9;
									out1_tmp.PourcentageVoix_Inscrit9 = row1.PourcentageVoix_Inscrit9;
									out1_tmp.PourcentageVoix_Exprime9 = row1.PourcentageVoix_Exprime9;
									out1_tmp.NumeroPanneau10 = row1.NumeroPanneau10;
									out1_tmp.Sexe10 = row1.Sexe10;
									out1_tmp.Nom10 = row1.Nom10;
									out1_tmp.Prenom10 = row1.Prenom10;
									out1_tmp.Voix10 = row1.Voix10;
									out1_tmp.PourcentageVoix_Inscrit10 = row1.PourcentageVoix_Inscrit10;
									out1_tmp.PourcentageVoix_Exprime10 = row1.PourcentageVoix_Exprime10;
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

									String[] row_tLogRow_1 = new String[96];

									row_tLogRow_1[0] = String.valueOf(out1.Id);

									if (out1.CodeDepartement != null) { //
										row_tLogRow_1[1] = String.valueOf(out1.CodeDepartement);

									} //

									if (out1.LibelleDepartement != null) { //
										row_tLogRow_1[2] = String.valueOf(out1.LibelleDepartement);

									} //

									if (out1.CodeCirconscription != null) { //
										row_tLogRow_1[3] = String.valueOf(out1.CodeCirconscription);

									} //

									if (out1.LibelleCirconscription != null) { //
										row_tLogRow_1[4] = String.valueOf(out1.LibelleCirconscription);

									} //

									if (out1.Inscrit != null) { //
										row_tLogRow_1[5] = String.valueOf(out1.Inscrit);

									} //

									if (out1.Abstention != null) { //
										row_tLogRow_1[6] = String.valueOf(out1.Abstention);

									} //

									if (out1.PourcentageAbstention_Inscrit != null) { //
										row_tLogRow_1[7] = out1.PourcentageAbstention_Inscrit.toPlainString();

									} //

									if (out1.Votant != null) { //
										row_tLogRow_1[8] = String.valueOf(out1.Votant);

									} //

									if (out1.PourcentageVotant_Inscrit != null) { //
										row_tLogRow_1[9] = out1.PourcentageVotant_Inscrit.toPlainString();

									} //

									if (out1.VoteBlanc != null) { //
										row_tLogRow_1[10] = String.valueOf(out1.VoteBlanc);

									} //

									if (out1.PourcentageBlanc_Inscrit != null) { //
										row_tLogRow_1[11] = out1.PourcentageBlanc_Inscrit.toPlainString();

									} //

									if (out1.PourcentageBlanc_Votant != null) { //
										row_tLogRow_1[12] = out1.PourcentageBlanc_Votant.toPlainString();

									} //

									if (out1.VoteNul != null) { //
										row_tLogRow_1[13] = String.valueOf(out1.VoteNul);

									} //

									if (out1.PourcentageNul_Inscrit != null) { //
										row_tLogRow_1[14] = out1.PourcentageNul_Inscrit.toPlainString();

									} //

									if (out1.PourcentageNul_Votant != null) { //
										row_tLogRow_1[15] = out1.PourcentageNul_Votant.toPlainString();

									} //

									if (out1.Exprime != null) { //
										row_tLogRow_1[16] = String.valueOf(out1.Exprime);

									} //

									if (out1.PourcentageExprime_Inscrit != null) { //
										row_tLogRow_1[17] = out1.PourcentageExprime_Inscrit.toPlainString();

									} //

									if (out1.PourcentageExprime_Votant != null) { //
										row_tLogRow_1[18] = out1.PourcentageExprime_Votant.toPlainString();

									} //

									if (out1.NumeroPanneau != null) { //
										row_tLogRow_1[19] = String.valueOf(out1.NumeroPanneau);

									} //

									if (out1.Sexe != null) { //
										row_tLogRow_1[20] = String.valueOf(out1.Sexe);

									} //

									if (out1.Nom != null) { //
										row_tLogRow_1[21] = String.valueOf(out1.Nom);

									} //

									if (out1.Prenom != null) { //
										row_tLogRow_1[22] = String.valueOf(out1.Prenom);

									} //

									if (out1.Voix != null) { //
										row_tLogRow_1[23] = String.valueOf(out1.Voix);

									} //

									if (out1.PourcentageVoix_Inscrit != null) { //
										row_tLogRow_1[24] = out1.PourcentageVoix_Inscrit.toPlainString();

									} //

									if (out1.PourcentageVoix_Exprime != null) { //
										row_tLogRow_1[25] = out1.PourcentageVoix_Exprime.toPlainString();

									} //

									if (out1.NumeroPanneau1 != null) { //
										row_tLogRow_1[26] = String.valueOf(out1.NumeroPanneau1);

									} //

									if (out1.Sexe1 != null) { //
										row_tLogRow_1[27] = String.valueOf(out1.Sexe1);

									} //

									if (out1.Nom1 != null) { //
										row_tLogRow_1[28] = String.valueOf(out1.Nom1);

									} //

									if (out1.Prenom1 != null) { //
										row_tLogRow_1[29] = String.valueOf(out1.Prenom1);

									} //

									if (out1.Voix1 != null) { //
										row_tLogRow_1[30] = String.valueOf(out1.Voix1);

									} //

									if (out1.PourcentageVoix_Inscrit1 != null) { //
										row_tLogRow_1[31] = out1.PourcentageVoix_Inscrit1.toPlainString();

									} //

									if (out1.PourcentageVoix_Exprime1 != null) { //
										row_tLogRow_1[32] = out1.PourcentageVoix_Exprime1.toPlainString();

									} //

									if (out1.NumeroPanneau2 != null) { //
										row_tLogRow_1[33] = String.valueOf(out1.NumeroPanneau2);

									} //

									if (out1.Sexe2 != null) { //
										row_tLogRow_1[34] = String.valueOf(out1.Sexe2);

									} //

									if (out1.Nom2 != null) { //
										row_tLogRow_1[35] = String.valueOf(out1.Nom2);

									} //

									if (out1.Prenom2 != null) { //
										row_tLogRow_1[36] = String.valueOf(out1.Prenom2);

									} //

									if (out1.Voix2 != null) { //
										row_tLogRow_1[37] = String.valueOf(out1.Voix2);

									} //

									if (out1.PourcentageVoix_Inscrit2 != null) { //
										row_tLogRow_1[38] = out1.PourcentageVoix_Inscrit2.toPlainString();

									} //

									if (out1.PourcentageVoix_Exprime2 != null) { //
										row_tLogRow_1[39] = out1.PourcentageVoix_Exprime2.toPlainString();

									} //

									if (out1.NumeroPanneau3 != null) { //
										row_tLogRow_1[40] = String.valueOf(out1.NumeroPanneau3);

									} //

									if (out1.Sexe3 != null) { //
										row_tLogRow_1[41] = String.valueOf(out1.Sexe3);

									} //

									if (out1.Nom3 != null) { //
										row_tLogRow_1[42] = String.valueOf(out1.Nom3);

									} //

									if (out1.Prenom3 != null) { //
										row_tLogRow_1[43] = String.valueOf(out1.Prenom3);

									} //

									if (out1.Voix3 != null) { //
										row_tLogRow_1[44] = String.valueOf(out1.Voix3);

									} //

									if (out1.PourcentageVoix_Inscrit3 != null) { //
										row_tLogRow_1[45] = out1.PourcentageVoix_Inscrit3.toPlainString();

									} //

									if (out1.PourcentageVoix_Exprime3 != null) { //
										row_tLogRow_1[46] = out1.PourcentageVoix_Exprime3.toPlainString();

									} //

									if (out1.NumeroPanneau4 != null) { //
										row_tLogRow_1[47] = String.valueOf(out1.NumeroPanneau4);

									} //

									if (out1.Sexe4 != null) { //
										row_tLogRow_1[48] = String.valueOf(out1.Sexe4);

									} //

									if (out1.Nom4 != null) { //
										row_tLogRow_1[49] = String.valueOf(out1.Nom4);

									} //

									if (out1.Prenom4 != null) { //
										row_tLogRow_1[50] = String.valueOf(out1.Prenom4);

									} //

									if (out1.Voix4 != null) { //
										row_tLogRow_1[51] = String.valueOf(out1.Voix4);

									} //

									if (out1.PourcentageVoix_Inscrit4 != null) { //
										row_tLogRow_1[52] = out1.PourcentageVoix_Inscrit4.toPlainString();

									} //

									if (out1.PourcentageVoix_Exprime4 != null) { //
										row_tLogRow_1[53] = out1.PourcentageVoix_Exprime4.toPlainString();

									} //

									if (out1.NumeroPanneau5 != null) { //
										row_tLogRow_1[54] = String.valueOf(out1.NumeroPanneau5);

									} //

									if (out1.Sexe5 != null) { //
										row_tLogRow_1[55] = String.valueOf(out1.Sexe5);

									} //

									if (out1.Nom5 != null) { //
										row_tLogRow_1[56] = String.valueOf(out1.Nom5);

									} //

									if (out1.Prenom5 != null) { //
										row_tLogRow_1[57] = String.valueOf(out1.Prenom5);

									} //

									if (out1.Voix5 != null) { //
										row_tLogRow_1[58] = String.valueOf(out1.Voix5);

									} //

									if (out1.PourcentageVoix_Inscrit5 != null) { //
										row_tLogRow_1[59] = out1.PourcentageVoix_Inscrit5.toPlainString();

									} //

									if (out1.PourcentageVoix_Exprime5 != null) { //
										row_tLogRow_1[60] = out1.PourcentageVoix_Exprime5.toPlainString();

									} //

									if (out1.NumeroPanneau6 != null) { //
										row_tLogRow_1[61] = String.valueOf(out1.NumeroPanneau6);

									} //

									if (out1.Sexe6 != null) { //
										row_tLogRow_1[62] = String.valueOf(out1.Sexe6);

									} //

									if (out1.Nom6 != null) { //
										row_tLogRow_1[63] = String.valueOf(out1.Nom6);

									} //

									if (out1.Prenom6 != null) { //
										row_tLogRow_1[64] = String.valueOf(out1.Prenom6);

									} //

									if (out1.Voix6 != null) { //
										row_tLogRow_1[65] = String.valueOf(out1.Voix6);

									} //

									if (out1.PourcentageVoix_Inscrit6 != null) { //
										row_tLogRow_1[66] = out1.PourcentageVoix_Inscrit6.toPlainString();

									} //

									if (out1.PourcentageVoix_Exprime6 != null) { //
										row_tLogRow_1[67] = out1.PourcentageVoix_Exprime6.toPlainString();

									} //

									if (out1.NumeroPanneau7 != null) { //
										row_tLogRow_1[68] = String.valueOf(out1.NumeroPanneau7);

									} //

									if (out1.Sexe7 != null) { //
										row_tLogRow_1[69] = String.valueOf(out1.Sexe7);

									} //

									if (out1.Nom7 != null) { //
										row_tLogRow_1[70] = String.valueOf(out1.Nom7);

									} //

									if (out1.Prenom7 != null) { //
										row_tLogRow_1[71] = String.valueOf(out1.Prenom7);

									} //

									if (out1.Voix7 != null) { //
										row_tLogRow_1[72] = String.valueOf(out1.Voix7);

									} //

									if (out1.PourcentageVoix_Inscrit7 != null) { //
										row_tLogRow_1[73] = out1.PourcentageVoix_Inscrit7.toPlainString();

									} //

									if (out1.PourcentageVoix_Exprime7 != null) { //
										row_tLogRow_1[74] = out1.PourcentageVoix_Exprime7.toPlainString();

									} //

									if (out1.NumeroPanneau8 != null) { //
										row_tLogRow_1[75] = String.valueOf(out1.NumeroPanneau8);

									} //

									if (out1.Sexe8 != null) { //
										row_tLogRow_1[76] = String.valueOf(out1.Sexe8);

									} //

									if (out1.Nom8 != null) { //
										row_tLogRow_1[77] = String.valueOf(out1.Nom8);

									} //

									if (out1.Prenom8 != null) { //
										row_tLogRow_1[78] = String.valueOf(out1.Prenom8);

									} //

									if (out1.Voix8 != null) { //
										row_tLogRow_1[79] = String.valueOf(out1.Voix8);

									} //

									if (out1.PourcentageVoix_Inscrit8 != null) { //
										row_tLogRow_1[80] = out1.PourcentageVoix_Inscrit8.toPlainString();

									} //

									if (out1.PourcentageVoix_Exprime8 != null) { //
										row_tLogRow_1[81] = out1.PourcentageVoix_Exprime8.toPlainString();

									} //

									if (out1.NumeroPanneau9 != null) { //
										row_tLogRow_1[82] = String.valueOf(out1.NumeroPanneau9);

									} //

									if (out1.Sexe9 != null) { //
										row_tLogRow_1[83] = String.valueOf(out1.Sexe9);

									} //

									if (out1.Nom9 != null) { //
										row_tLogRow_1[84] = String.valueOf(out1.Nom9);

									} //

									if (out1.Prenom9 != null) { //
										row_tLogRow_1[85] = String.valueOf(out1.Prenom9);

									} //

									if (out1.Voix9 != null) { //
										row_tLogRow_1[86] = String.valueOf(out1.Voix9);

									} //

									if (out1.PourcentageVoix_Inscrit9 != null) { //
										row_tLogRow_1[87] = out1.PourcentageVoix_Inscrit9.toPlainString();

									} //

									if (out1.PourcentageVoix_Exprime9 != null) { //
										row_tLogRow_1[88] = out1.PourcentageVoix_Exprime9.toPlainString();

									} //

									if (out1.NumeroPanneau10 != null) { //
										row_tLogRow_1[89] = String.valueOf(out1.NumeroPanneau10);

									} //

									if (out1.Sexe10 != null) { //
										row_tLogRow_1[90] = String.valueOf(out1.Sexe10);

									} //

									if (out1.Nom10 != null) { //
										row_tLogRow_1[91] = String.valueOf(out1.Nom10);

									} //

									if (out1.Prenom10 != null) { //
										row_tLogRow_1[92] = String.valueOf(out1.Prenom10);

									} //

									if (out1.Voix10 != null) { //
										row_tLogRow_1[93] = String.valueOf(out1.Voix10);

									} //

									if (out1.PourcentageVoix_Inscrit10 != null) { //
										row_tLogRow_1[94] = out1.PourcentageVoix_Inscrit10.toPlainString();

									} //

									if (out1.PourcentageVoix_Exprime10 != null) { //
										row_tLogRow_1[95] = out1.PourcentageVoix_Exprime10.toPlainString();

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
									row4.CodeDepartement = row2.CodeDepartement;
									row4.LibelleDepartement = row2.LibelleDepartement;
									row4.CodeCirconscription = row2.CodeCirconscription;
									row4.LibelleCirconscription = row2.LibelleCirconscription;
									row4.Inscrit = row2.Inscrit;
									row4.Abstention = row2.Abstention;
									row4.PourcentageAbstention_Inscrit = row2.PourcentageAbstention_Inscrit;
									row4.Votant = row2.Votant;
									row4.PourcentageVotant_Inscrit = row2.PourcentageVotant_Inscrit;
									row4.VoteBlanc = row2.VoteBlanc;
									row4.PourcentageBlanc_Inscrit = row2.PourcentageBlanc_Inscrit;
									row4.PourcentageBlanc_Votant = row2.PourcentageBlanc_Votant;
									row4.VoteNul = row2.VoteNul;
									row4.PourcentageNul_Inscrit = row2.PourcentageNul_Inscrit;
									row4.PourcentageNul_Votant = row2.PourcentageNul_Votant;
									row4.Exprime = row2.Exprime;
									row4.PourcentageExprime_Inscrit = row2.PourcentageExprime_Inscrit;
									row4.PourcentageExprime_Votant = row2.PourcentageExprime_Votant;
									row4.NumeroPanneau = row2.NumeroPanneau;
									row4.Sexe = row2.Sexe;
									row4.Nom = row2.Nom;
									row4.Prenom = row2.Prenom;
									row4.Voix = row2.Voix;
									row4.PourcentageVoix_Inscrit = row2.PourcentageVoix_Inscrit;
									row4.PourcentageVoix_Exprime = row2.PourcentageVoix_Exprime;
									row4.NumeroPanneau1 = row2.NumeroPanneau1;
									row4.Sexe1 = row2.Sexe1;
									row4.Nom1 = row2.Nom1;
									row4.Prenom1 = row2.Prenom1;
									row4.Voix1 = row2.Voix1;
									row4.PourcentageVoix_Inscrit1 = row2.PourcentageVoix_Inscrit1;
									row4.PourcentageVoix_Exprime1 = row2.PourcentageVoix_Exprime1;
									row4.NumeroPanneau2 = row2.NumeroPanneau2;
									row4.Sexe2 = row2.Sexe2;
									row4.Nom2 = row2.Nom2;
									row4.Prenom2 = row2.Prenom2;
									row4.Voix2 = row2.Voix2;
									row4.PourcentageVoix_Inscrit2 = row2.PourcentageVoix_Inscrit2;
									row4.PourcentageVoix_Exprime2 = row2.PourcentageVoix_Exprime2;
									row4.NumeroPanneau3 = row2.NumeroPanneau3;
									row4.Sexe3 = row2.Sexe3;
									row4.Nom3 = row2.Nom3;
									row4.Prenom3 = row2.Prenom3;
									row4.Voix3 = row2.Voix3;
									row4.PourcentageVoix_Inscrit3 = row2.PourcentageVoix_Inscrit3;
									row4.PourcentageVoix_Exprime3 = row2.PourcentageVoix_Exprime3;
									row4.NumeroPanneau4 = row2.NumeroPanneau4;
									row4.Sexe4 = row2.Sexe4;
									row4.Nom4 = row2.Nom4;
									row4.Prenom4 = row2.Prenom4;
									row4.Voix4 = row2.Voix4;
									row4.PourcentageVoix_Inscrit4 = row2.PourcentageVoix_Inscrit4;
									row4.PourcentageVoix_Exprime4 = row2.PourcentageVoix_Exprime4;
									row4.NumeroPanneau5 = row2.NumeroPanneau5;
									row4.Sexe5 = row2.Sexe5;
									row4.Nom5 = row2.Nom5;
									row4.Prenom5 = row2.Prenom5;
									row4.Voix5 = row2.Voix5;
									row4.PourcentageVoix_Inscrit5 = row2.PourcentageVoix_Inscrit5;
									row4.PourcentageVoix_Exprime5 = row2.PourcentageVoix_Exprime5;
									row4.NumeroPanneau6 = row2.NumeroPanneau6;
									row4.Sexe6 = row2.Sexe6;
									row4.Nom6 = row2.Nom6;
									row4.Prenom6 = row2.Prenom6;
									row4.Voix6 = row2.Voix6;
									row4.PourcentageVoix_Inscrit6 = row2.PourcentageVoix_Inscrit6;
									row4.PourcentageVoix_Exprime6 = row2.PourcentageVoix_Exprime6;
									row4.NumeroPanneau7 = row2.NumeroPanneau7;
									row4.Sexe7 = row2.Sexe7;
									row4.Nom7 = row2.Nom7;
									row4.Prenom7 = row2.Prenom7;
									row4.Voix7 = row2.Voix7;
									row4.PourcentageVoix_Inscrit7 = row2.PourcentageVoix_Inscrit7;
									row4.PourcentageVoix_Exprime7 = row2.PourcentageVoix_Exprime7;
									row4.NumeroPanneau8 = row2.NumeroPanneau8;
									row4.Sexe8 = row2.Sexe8;
									row4.Nom8 = row2.Nom8;
									row4.Prenom8 = row2.Prenom8;
									row4.Voix8 = row2.Voix8;
									row4.PourcentageVoix_Inscrit8 = row2.PourcentageVoix_Inscrit8;
									row4.PourcentageVoix_Exprime8 = row2.PourcentageVoix_Exprime8;
									row4.NumeroPanneau9 = row2.NumeroPanneau9;
									row4.Sexe9 = row2.Sexe9;
									row4.Nom9 = row2.Nom9;
									row4.Prenom9 = row2.Prenom9;
									row4.Voix9 = row2.Voix9;
									row4.PourcentageVoix_Inscrit9 = row2.PourcentageVoix_Inscrit9;
									row4.PourcentageVoix_Exprime9 = row2.PourcentageVoix_Exprime9;
									row4.NumeroPanneau10 = row2.NumeroPanneau10;
									row4.Sexe10 = row2.Sexe10;
									row4.Nom10 = row2.Nom10;
									row4.Prenom10 = row2.Prenom10;
									row4.Voix10 = row2.Voix10;
									row4.PourcentageVoix_Inscrit10 = row2.PourcentageVoix_Inscrit10;
									row4.PourcentageVoix_Exprime10 = row2.PourcentageVoix_Exprime10;

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
									fileOutputDelimitedUtil_tFileOutputDelimited_1.putValue_0(row4,
											sb_tFileOutputDelimited_1, OUT_DELIM_tFileOutputDelimited_1);
									fileOutputDelimitedUtil_tFileOutputDelimited_1.putValue_1(row4,
											sb_tFileOutputDelimited_1, OUT_DELIM_tFileOutputDelimited_1);
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
							 * [tFileInputExcel_1 process_data_end ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_1 end ] start
							 */

							currentComponent = "tFileInputExcel_1";

						}

						globalMap.put("tFileInputExcel_1_NB_LINE", nb_line_tFileInputExcel_1);

					}

				} finally {

					if (!(source_tFileInputExcel_1 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_1.close();
					}

				}

				ok_Hash.put("tFileInputExcel_1", true);
				end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_1 end ] stop
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
				 * [tFileInputExcel_1 finally ] start
				 */

				currentComponent = "tFileInputExcel_1";

				/**
				 * [tFileInputExcel_1 finally ] stop
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

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
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
		final VoteElection2017 VoteElection2017Class = new VoteElection2017();

		int exitCode = VoteElection2017Class.runJobInTOS(args);

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
			java.io.InputStream inContext = VoteElection2017.class.getClassLoader()
					.getResourceAsStream("datamspr/voteelection2017_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = VoteElection2017.class.getClassLoader()
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
			tFileInputExcel_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputExcel_1) {
			globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", -1);

			e_tFileInputExcel_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : VoteElection2017");
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
 * 443334 characters generated by Talend Open Studio for Data Integration on the
 * 15 avril 2024, 22:04:58 CEST
 ************************************************************************************************/