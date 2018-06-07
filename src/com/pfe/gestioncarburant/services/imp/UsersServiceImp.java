package com.pfe.gestioncarburant.services.imp;

import java.util.List;

import org.apache.commons.validator.EmailValidator;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pfe.gestioncarburant.dao.EmployeDao;
import com.pfe.gestioncarburant.dao.UsersDao;
import com.pfe.gestioncarburant.entities.Employee;
import com.pfe.gestioncarburant.entities.User;
import com.pfe.gestioncarburant.services.UsersService;
import com.pfe.gestioncarburant.utils.SendEmail;

@Service
public class UsersServiceImp implements UsersService {

	@Autowired
	private UsersDao usersDao;
	@Autowired
	private EmployeDao employeDao;

	// Retour null si les infos correcte
	public String[] isValid(User user) {
		String[] res = new String[2];
		// Si longeur num CIN invalide

		if (user.getEmployee().getCin().length() != 8) {
			res[0] = "0";
			res[1] = "CIN Invalide";
			return res;
		}
		// Si format email invalide
		if (user.getEmployee().getEmail() == null
				|| !EmailValidator.getInstance().isValid(user.getEmployee().getEmail())) {
			res[0] = "0";
			res[1] = "E-Mail Invalide";
			return res;
		}
		// Si numéro tel invalide
		if (user.getEmployee().getTel() == null || user.getEmployee().getTel().length() != 8) {
			res[0] = "0";
			res[1] = "Numéro de téléphoe Invalide";
			return res;
		}
		return null;

	}

	@Override
	public String[] save(User user) throws Exception {
		if (isValid(user) != null) {
			return isValid(user);
		}

		String[] result = new String[2];
		Criterion critMat, critCin, critLogin, critMail;
		critMat = Restrictions.idEq(user.getEmployee().getMatricule());
		critCin = Restrictions.eq("cin", user.getEmployee().getCin());
		critLogin = Restrictions.eq("login", user.getLogin());
		critMail = Restrictions.eq("email", user.getEmployee().getEmail());

		// Si user existe

		List<User> list = usersDao.findByCriteria(User.class, critMat);

		if (!list.isEmpty()) {
			result[0] = "0";
			result[1] = "Matricule existant";
			return result;

		}
		// Si matricule existe

		list = usersDao.findByCriteria(Employee.class, critCin);
		if (!list.isEmpty()) {
			result[0] = "0";
			result[1] = "CIN existant";
			return result;

		}

		// Si login existe

		list = usersDao.findByCriteria(User.class, critLogin);
		if (!list.isEmpty()) {
			result[0] = "0";
			result[1] = "Login existant";
			return result;

		}

		// Si mail existe

		list = usersDao.findByCriteria(Employee.class, critMail);
		if (!list.isEmpty()) {
			result[0] = "0";
			result[1] = "E-mail existant";
			return result;

		}
		usersDao.save(user.getEmployee());
		usersDao.save(user);

		String subject = "Compte ";
		String object = "Salut Mr(me)" + user.getEmployee().getNom() + user.getEmployee().getPrenom()
				+ "\nVoici vos cordonnées de connexion :\n" + "-Nom d'utilsateur :" + user.getLogin()
				+ "\n-Mot de passe :" + user.getPassword();
		SendEmail.envoyer(user.getEmployee().getEmail(), object, subject);
		result[0] = "1";
		result[1] = "Ajout effectué";
		return result;

	}

	@Override
	public String[] update(User user) throws Exception {
		if (isValid(user) != null) {
			return isValid(user);
		}

		String[] result = new String[2];
		Criterion crit1 = Restrictions.idEq(user.getEmployee().getMatricule());
		Criterion crit = Restrictions.not(crit1);
		Criterion crit2 = Restrictions.eq("cin", user.getEmployee().getCin());
		Criterion crit3 = Restrictions.eq("email", user.getEmployee().getEmail());
		Criterion crit4 = Restrictions.eq("login", user.getLogin());

		List<User> list = usersDao.findByProperty(user);

		if (list.isEmpty()) {
			list = usersDao.findByCriteria(Employee.class, crit, crit2);
			if (!list.isEmpty()) {
				result[0] = "1";
				result[1] = "CIN existant";
				return result;
			}

			list = usersDao.findByCriteria(User.class, crit, crit4);
			if (!list.isEmpty()) {
				result[0] = "1";
				result[1] = "Login existant";
				return result;
			}

			list = usersDao.findByCriteria(Employee.class, crit, crit3);
			if (list.size() == 1) {
				result[0] = "1";
				result[1] = "E-mail existant";
				return result;
			}

		}

		usersDao.saveOrUpdate(user.getEmployee());
		usersDao.saveOrUpdate(user);

		String subject = "Compte ";
		String object = "Salut Mr(me)" + user.getEmployee().getNom() + user.getEmployee().getPrenom()
				+ "\nVoici vos cordonnées de connexion :\n" + "-Nom d'utilsateur :" + user.getLogin()
				+ "\n-Mot de passe :" + user.getPassword();

		SendEmail.envoyer(user.getEmployee().getEmail(), object, subject);
		result[0] = "1";
		result[1] = "Modification effectué";
		return result;

	}

	@Override
	public void delete(User user) throws Exception {

		usersDao.delete(user);
		employeDao.delete(user.getEmployee());

	}

	@Override
	public List<User> findAll() throws Exception {
		return usersDao.findAll(User.class);
	}

	@Override
	public User findByLogin(String login) throws UsernameNotFoundException {
		User user = new User();
		List<User> list;
		try {
			list = usersDao.findByCriteria(User.class, Restrictions.eq("login", login));
			if (!list.isEmpty()) {
				user = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

}
