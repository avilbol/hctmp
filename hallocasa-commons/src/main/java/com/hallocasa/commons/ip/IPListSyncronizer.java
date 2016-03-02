package com.hallocasa.commons.ip;

import java.util.ArrayList;
import java.util.List;

public class IPListSyncronizer {

	/* static fields */
	public static class Result<T extends IPEntity> {
		private final List<T> synchronizedList;
		private final List<T> toRemoveList;
		private final List<T> toPersist;

		/**
		 * Constructor
		 * 
		 * @param synchronizedList
		 * @param toRemoveList
		 * @param toPersist
		 */
		public Result(List<T> synchronizedList, List<T> toRemoveList,
			List<T> toPersist) {
			super();
			this.synchronizedList = synchronizedList;
			this.toRemoveList = toRemoveList;
			this.toPersist = toPersist;
		}

		/**
		 * Getter for synchronizedList
		 * 
		 * @return the synchronizedList
		 */
		public List<T> getSynchronizedList() {
			return synchronizedList;
		}

		/**
		 * Getter for toRemoveList
		 * 
		 * @return the toRemoveList
		 */
		public List<T> getToRemoveList() {
			return toRemoveList;
		}

		/**
		 * Getter for toPersist
		 * 
		 * @return the toPersist
		 */
		public List<T> getToPersist() {
			return toPersist;
		}

	}

	/* instance variables */

	/* constructors */
	private IPListSyncronizer() {
	}

	/* Methods */

	/**
	 * Synchronize the list given for a IP String list, into the entity
	 * detecting which entities have to be removed, and which ones have to be
	 * persisted
	 * 
	 * @param currentIpList The current list of IPs in the entity
	 * @param ipsToSet List of string IPs to replace into the entity-
	 * @param ipEntityClass Class of the IP entity
	 * @return 3 lists, the first is the list of entities to be persisted, the
	 *         second one is the list of entities to be removed, and the third
	 *         one is the complete synchronized list
	 */
	public static <T extends IPEntity> Result<T> synchronizeListInEntity(
		List<T> currentIpList, List<String> ipsToSet, Class<T> ipEntityClass) {

		if (currentIpList == null) {
			currentIpList = new ArrayList<T>();
		}
		if (ipsToSet == null) {
			ipsToSet = new ArrayList<String>();
		}
		// initialize variables
		List<T> synchronizedList = new ArrayList<T>();
		List<T> toRemoveList = new ArrayList<T>();
		List<T> toPersist = new ArrayList<T>();

		List<String> newIps = new ArrayList<String>(ipsToSet);

		// find IPs which are in the partner and not in the new list
		// and find IPs which aren't in the partner and are in the new list
		for (T ip : currentIpList) {
			if (!newIps.contains(ip.getIp())) {
				toRemoveList.add(ip);
			} else {
				newIps.remove(ip.getIp());
				synchronizedList.add(ip);
			}
		}

		// remaining IP in the newList must be created
		try {
			for (String newIp : newIps) {
				T newPartnerWhiteIp = ipEntityClass.newInstance();
				newPartnerWhiteIp.setIp(newIp);
				synchronizedList.add(newPartnerWhiteIp);
				toPersist.add(newPartnerWhiteIp);
			}
		} catch (InstantiationError | InstantiationException
			| IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}

		return new Result<T>(synchronizedList, toRemoveList, toPersist);
	}

	/* Getters & Setters */
}
