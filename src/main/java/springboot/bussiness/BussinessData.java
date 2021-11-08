package springboot.bussiness;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Configurable;

import springboot.config.ApplicationContextUtils;
import springboot.config.YAMLConfig;
import springboot.hbn.entities.Branch;
import springboot.hbn.entities.TblBanks;
import springboot.hbn.entities.TblBlackList;
import springboot.hbn.entities.TblBorrower;
import springboot.hbn.entities.TblBrand;
import springboot.hbn.entities.TblExtraFee2;
import springboot.hbn.entities.TblInsuranceBanks;
import springboot.hbn.entities.TblInsuranceFee;
import springboot.hbn.entities.TblInsuranceFeeApi;
import springboot.hbn.entities.TblInsuranceFeeMapp;
import springboot.hbn.entities.TblInsuranceFeeMappApi;
import springboot.hbn.entities.TblInsuranceProviders;
import springboot.hbn.entities.TblInsuranceProvidersApi;
import springboot.hbn.entities.TblProduct2;
import springboot.hbn.entities.TblQuestions;
import springboot.hbn.entities.TblRateConfig2;
import springboot.hbn.entities.TblSponsor;
import springboot.hbn.entities.TblSystemConfigurations;
import springboot.hbn.entities.TransasctionRoom;
import springboot.hbn.home.TblProductHome;
import springboot.hbn.home.TblSponsorHome;
import springboot.logs.Logs;
import springboot.mongo.dao.BaseMongo;
import springboot.utils.GsonUltilities;

@Configurable
public class BussinessData {

	/**
	 * static Singleton instance.
	 */
	private static volatile BussinessData instance;
	final static Logs LOGGER = new Logs(BussinessData.class);

	final TblProductHome PRODUCTHOME = new TblProductHome();
	final Integer DATA_MAX_SIZE_IN_QUERY = 100;

	/**
	 * Private constructor for singleton.
	 */
	private BussinessData() {
	}

	/**
	 * Return a singleton instance of BussinessData.
	 */
	public static BussinessData getInstance() {
		// Double lock for thread safety.
		if (instance == null) {
			synchronized (BussinessData.class) {
				if (instance == null) {
					instance = new BussinessData();
				}
			}
		}
		return instance;
	}

	YAMLConfig yamlConfig = ApplicationContextUtils.getApplicationContext().getBean(YAMLConfig.class);

	public void loadProducts() {
		try {
			String collection = "tbl_product";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblProduct2());
			BaseMongo.getInstance().cleanCollection(collection);
			List docList = new ArrayList();
			for (Object obj : lstSponsor) {
				try {
					TblProduct2 tblProduct = (TblProduct2) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("product_id", tblProduct.getProductId());
//					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					Long updateRs = new Long(-1);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getProductId() + "_"
								+ tblProduct.getProductName());
						Document document_ = Document.parse(json);
//						BaseMongo.getInstance().insertDocument(collection, document_);
						docList.add(document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getProductId());
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			BaseMongo.getInstance().insertMany(collection, docList);
			LOGGER.info("load complete:" + collection);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	public void loadExtraFee() {
		try {
			String collection = "tbl_extra_fee";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblExtraFee2());
			BaseMongo.getInstance().cleanCollection(collection);
			List docList = new ArrayList();
			for (Object obj : lstSponsor) {
				try {
					TblExtraFee2 tblProduct = (TblExtraFee2) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("extra_fee_id", tblProduct.getExtraFeeId());
					Long updateRs = new Long(-1); 
//							BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getExtraFeeId() + "_"
								+ tblProduct.getExtraFeeName());
						Document document_ = Document.parse(json);
						
						docList.add(document_);
//						BaseMongo.getInstance().insertDocument(collection, document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getExtraFeeId());
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			BaseMongo.getInstance().insertMany(collection, docList);
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	public void loadBorrower() {
		try {
			String collection = "borrower";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblBorrower());
			BaseMongo.getInstance().cleanCollection(collection);
			List docList = new ArrayList();
			for (Object obj : lstSponsor) {
				try {
					TblBorrower tblProduct = (TblBorrower) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("borrower_id", tblProduct.getBorrowerId());
					Long updateRs = new Long(-1); 
//							BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getBorrowerId() + "_"
								+ tblProduct.getBorrowerName());
						Document document_ = Document.parse(json);
//						BaseMongo.getInstance().insertDocument(collection, document_);
						docList.add(document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getBorrowerId());
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					LOGGER.fatal("loadBorrower",e);
				}
			}
			BaseMongo.getInstance().insertMany(collection, docList);
			LOGGER.info("load complete:" + collection);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	public void loadQuestions() {
		try {
			String collection = "tbl_questions";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblQuestions());
			BaseMongo.getInstance().cleanCollection(collection);
			List docList = new ArrayList();
			for (Object obj : lstSponsor) {
				try {
					TblQuestions tblProduct = (TblQuestions) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("question_id", tblProduct.getQuestionId());
//					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					Long updateRs = new Long(-1); 
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getQuestionId() + "_"
								+ tblProduct.getQuestionShortDesc());
						Document document_ = Document.parse(json);
//						BaseMongo.getInstance().insertDocument(collection, document_);
						docList.add(document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getQuestionId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					LOGGER.fatal("loadBorrower",e);
				}
			}
			BaseMongo.getInstance().insertMany(collection, docList);
			LOGGER.info("load complete "+collection);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadQuestions", e);
			e.printStackTrace();
		}
	}

	public void loadRateConfig() {
		try {
			String collection = "tbl_rate_config";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblRateConfig2());
			BaseMongo.getInstance().cleanCollection(collection);
			for (Object obj : lstSponsor) {
				try {
					TblRateConfig2 tblProduct = (TblRateConfig2) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("rate_id", tblProduct.getRateId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println(
								"update fail, auto insert:" + tblProduct.getRateId() + "_" + tblProduct.getRateName());
						Document document_ = Document.parse(json);
						BaseMongo.getInstance().insertDocument(collection, document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getRateId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					LOGGER.fatal("loadRateConfig", e);
				}
			}
			LOGGER.info("load complete:" + collection);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	public void loadSponsor() {
		try {
			String collection = "tbl_sponsor";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblSponsor());
			BaseMongo.getInstance().cleanCollection(collection);
			for (Object obj : lstSponsor) {
				try {
					TblSponsor tblProduct = (TblSponsor) obj;
					tblProduct.setSponsor_en_name(TblSponsor.deAccent(tblProduct.getSponsorName()));
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("sponsor_id", tblProduct.getSponsorId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getSponsorId() + "_"
								+ tblProduct.getSponsorName());
						Document document_ = Document.parse(json);
						BaseMongo.getInstance().insertDocument(collection, document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getSponsorId());
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	/**
	 * Tính toán tổng số giao dịch đã giải ngân và đã tất toán của sponsor
	 */
	private List<TblSponsor> calculateSponsorStatistic() {
		try {
			List<TblSponsor> lstSponsorStatistic = new TblSponsorHome().getListSponsorStatistic();
			return lstSponsorStatistic;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public void loadSystemConfigure() {
		try {
			String collection = "tbl_system_configurations";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblSystemConfigurations());
			BaseMongo.getInstance().cleanCollection(collection);
			for (Object obj : lstSponsor) {
				try {
					TblSystemConfigurations tblProduct = (TblSystemConfigurations) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("config_id", tblProduct.getConfigId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getConfigId() + "_"
								+ tblProduct.getConfigName());
						Document document_ = Document.parse(json);
						BaseMongo.getInstance().insertDocument(collection, document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getConfigId());
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	public void loadTblBlackList() {
		try {
			String collection = "black_list";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblBlackList());
			BaseMongo.getInstance().cleanCollection(collection);
			List docList = new ArrayList();
			for (Object obj : lstSponsor) {
				try {
					TblBlackList tblProduct = (TblBlackList) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("black_list_id", tblProduct.getBlackListId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getBlackListId() + "_"
								+ tblProduct.getBorrowerName());
						Document document_ = Document.parse(json);
						docList.add(document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getBlackListId());
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			BaseMongo.getInstance().insertMany(collection, docList);
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	public void loadTblBank() {
		try {
			String collection = "tbl_banks";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblBanks());
			BaseMongo.getInstance().cleanCollection(collection);
			List docList = new ArrayList();
			for (Object obj : lstSponsor) {
				try {
					TblBanks tblProduct = (TblBanks) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("row_id", tblProduct.getRowId());
//					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					Long updateRs = new Long(-1);
					if (updateRs <= 0) {
						System.out.println(
								"update fail, auto insert:" + tblProduct.getRowId() + "_" + tblProduct.getBankName());
						Document document_ = Document.parse(json);
//						BaseMongo.getInstance().insertDocument(collection, document_);
						docList.add(document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getRowId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			BaseMongo.getInstance().insertMany(collection, docList);
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	public void loadTblBrand() {
		try {
			String collection = "tbl_brand";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblBrand());
			BaseMongo.getInstance().cleanCollection(collection);
			for (Object obj : lstSponsor) {
				try {
					TblBrand tblProduct = (TblBrand) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("brand_id", tblProduct.getBrandId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getBrandId() + "_"
								+ tblProduct.getBranchName());
						Document document_ = Document.parse(json);
						BaseMongo.getInstance().insertDocument(collection, document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getBrandId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	public void loadInsuranceProvider() {
		try {
			String collection = "tbl_insurance_providers";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblInsuranceProviders());
			BaseMongo.getInstance().cleanCollection(collection);
			for (Object obj : lstSponsor) {
				try {
					TblInsuranceProviders tblProduct = (TblInsuranceProviders) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("insurance_provider_id", tblProduct.getInsuranceProviderId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getInsuranceProviderId() + "_"
								+ tblProduct.getInsuranceName());
						Document document_ = Document.parse(json);
						BaseMongo.getInstance().insertDocument(collection, document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getInsuranceProviderId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	public void loadInsuranceProviderBanks() {
		try {
			String collection = "tbl_insurance_banks";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblInsuranceBanks());
			BaseMongo.getInstance().cleanCollection(collection);
			for (Object obj : lstSponsor) {
				try {
					TblInsuranceBanks tblProduct = (TblInsuranceBanks) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("mapping_id", tblProduct.getMappingId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getMappingId() + "_"
								+ tblProduct.getAccBankName());
						Document document_ = Document.parse(json);
						BaseMongo.getInstance().insertDocument(collection, document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getMappingId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	public void loanInsuranceFee() {
		try {
			String collection = "tbl_insurance_fee";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblInsuranceFee());
			BaseMongo.getInstance().cleanCollection(collection);
			for (Object obj : lstSponsor) {
				try {
					TblInsuranceFee tblProduct = (TblInsuranceFee) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("fee_id", tblProduct.getFeeId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println(
								"update fail, auto insert:" + tblProduct.getFeeId() + "_" + tblProduct.getFeeId());
						Document document_ = Document.parse(json);
						BaseMongo.getInstance().insertDocument(collection, document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getFeeId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	public void loanInsuranceFeeMapping() {
		try {
			String collection = "tbl_insurance_fee_mapp";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblInsuranceFeeMapp());
			BaseMongo.getInstance().cleanCollection(collection);
			for (Object obj : lstSponsor) {
				try {
					TblInsuranceFeeMapp tblProduct = (TblInsuranceFeeMapp) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("mapping_id", tblProduct.getMappingId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getMappingId() + "_"
								+ tblProduct.getMappingId());
						Document document_ = Document.parse(json);
						BaseMongo.getInstance().insertDocument(collection, document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getMappingId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}
	
	public void loadInsuranceProviderApi() {
		try {
			String collection = "tbl_insurance_providers_api";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblInsuranceProvidersApi());
			BaseMongo.getInstance().cleanCollection(collection);
			for (Object obj : lstSponsor) {
				try {
					TblInsuranceProvidersApi tblProduct = (TblInsuranceProvidersApi) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("insurance_provider_id", tblProduct.getInsuranceProviderId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getInsuranceProviderId() + "_"
								+ tblProduct.getInsuranceProviderId());
						Document document_ = Document.parse(json);
						BaseMongo.getInstance().insertDocument(collection, document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getInsuranceProviderId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}
	
	public void loanInsuranceFeeApi() {
		try {
			String collection = "tbl_insurance_fee_api";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblInsuranceFeeApi());
			BaseMongo.getInstance().cleanCollection(collection);
			for (Object obj : lstSponsor) {
				try {
					TblInsuranceFeeApi tblProduct = (TblInsuranceFeeApi) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("fee_id", tblProduct.getFeeId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getFeeId() + "_"
								+ tblProduct.getFeeId());
						Document document_ = Document.parse(json);
						BaseMongo.getInstance().insertDocument(collection, document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getFeeId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}
	
	public void loadBranch() {
		try {
			String collection = "branch";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new Branch());
			BaseMongo.getInstance().cleanCollection(collection);
			List docList = new ArrayList();
			for (Object obj : lstSponsor) {
				try {
					Branch tblProduct = (Branch) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("mapping_id", tblProduct.getRowId());
//					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					Long updateRs = new Long(-1);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getRowId() + "_"
								+ tblProduct.getRowId());
						Document document_ = Document.parse(json);
						docList.add(document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getRowId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			BaseMongo.getInstance().insertMany(collection, docList);
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}
	
	public void loadTransactionRoom() {
		try {
			String collection = "transasction_room";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TransasctionRoom());
			BaseMongo.getInstance().cleanCollection(collection);
			List docList = new ArrayList();
			for (Object obj : lstSponsor) {
				try {
					TransasctionRoom tblProduct = (TransasctionRoom) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("mapping_id", tblProduct.getRowId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getRowId() + "_"
								+ tblProduct.getRowId());
						Document document_ = Document.parse(json);
//						BaseMongo.getInstance().insertDocument(collection, document_);
						docList.add(document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getRowId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			BaseMongo.getInstance().insertMany(collection, docList);
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}
	public void loanInsuranceFeeMapApi() {
		try {
			String collection = "tbl_insurance_fee_mapp_api";
			List<Object> lstSponsor = PRODUCTHOME.listAllObject(new TblInsuranceFeeMappApi());
			BaseMongo.getInstance().cleanCollection(collection);
			for (Object obj : lstSponsor) {
				try {
					TblInsuranceFeeMappApi tblProduct = (TblInsuranceFeeMappApi) obj;
					String json = GsonUltilities.toJson(tblProduct);
					Bson filter = new Document("mapping_id", tblProduct.getMappingId());
					Long updateRs = BaseMongo.getInstance().updateByBson(collection, filter, json);
					if (updateRs <= 0) {
						System.out.println("update fail, auto insert:" + tblProduct.getMappingId() + "_"
								+ tblProduct.getMappingId());
						Document document_ = Document.parse(json);
						BaseMongo.getInstance().insertDocument(collection, document_);
					} else {
						System.out.println("update success cho:" + tblProduct.getMappingId());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			LOGGER.info("load complete:" + collection);
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.fatal("loadStableData", e);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		List<TblSponsor> lstSponsors1 = new ArrayList<TblSponsor>();
		List<TblSponsor> lstSponsors2 = new ArrayList<TblSponsor>();
		TblSponsor tblSponsor = new TblSponsor();
		tblSponsor.setSponsorId(10);
		tblSponsor.setSponsorCode("xxx");
		tblSponsor.setTotal_disbursement(0);
		tblSponsor.setTotal_settlement(0);
		TblSponsor tblSponsor1 = new TblSponsor();
		tblSponsor1.setSponsorId(11);
		tblSponsor1.setSponsorCode("xxx2");
		tblSponsor1.setTotal_disbursement(0);
		tblSponsor1.setTotal_settlement(0);

		TblSponsor tblSponsor3 = new TblSponsor();
		tblSponsor3.setSponsorId(10);
		tblSponsor3.setSponsorCode("xxx");
		tblSponsor3.setTotal_disbursement(100);
		tblSponsor3.setTotal_settlement(20);
		TblSponsor tblSponsor4 = new TblSponsor();
		tblSponsor4.setSponsorId(11);
		tblSponsor4.setSponsorCode("xxx2");
		tblSponsor4.setTotal_disbursement(100);
		tblSponsor4.setTotal_settlement(20);

		lstSponsors1.add(tblSponsor1);
		lstSponsors1.add(tblSponsor);

		lstSponsors2.add(tblSponsor3);
		lstSponsors2.add(tblSponsor4);

//		List<TblSponsor> lstList = union(lstSponsors1, lstSponsors2);
//		List<Object> newList = new ArrayList<>();
//		Stream.of(lstSponsors1, lstSponsors2).forEach(newList::addAll);
		List<TblSponsor> lstList = intersection(lstSponsors1, lstSponsors2);

		for (Object object : lstList) {
			System.out.println(GsonUltilities.toJson(object));
		}
	}

	private static List<TblSponsor> intersection(List<TblSponsor> users, List<TblSponsor> list) {

		return list.stream()
				.flatMap(OtherUser -> users.stream().filter(user -> user.getSponsorId() == OtherUser.getSponsorId()))
				.collect(Collectors.toList());
	}

	public static <T> List<T> union(List<T> list1, List<T> list2) {
		Set<T> set = new HashSet<T>();

		set.addAll(list1);
		set.addAll(list2);

		return new ArrayList<T>(set);
	}

}

class testFee {
	public ArrayList<Object> fee;
}
