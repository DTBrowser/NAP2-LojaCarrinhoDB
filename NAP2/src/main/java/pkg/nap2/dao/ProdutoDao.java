package pkg.nap2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import pkg.nap2.model.*;

public class ProdutoDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProdutoDao(Connection con) {
		this.con = con;
	}
	
	public List<Produto> getAllProducts(){
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			query = "select * from products";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				Produto row = new Produto();
				row.setId(rs.getInt("id"));
				row.setNome(rs.getString("name"));
				row.setCategoria(rs.getString("category"));
				row.setPreco(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				produtos.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return produtos;
	}
	
	
	public List<Carrinho> getCartProducts(ArrayList<Carrinho> carrinhoLista){
		List<Carrinho> produtos = new ArrayList<Carrinho>();
		
		try {
			if(carrinhoLista.size()>0) {
				for(Carrinho item:carrinhoLista) {
					query = "select * from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while(rs.next()) {
						Carrinho row = new Carrinho();
						row.setId(rs.getInt("id"));
						row.setNome(rs.getString("name"));
						row.setCategoria(rs.getString("category"));
						row.setPreco(rs.getDouble("price")*item.getQuantidade());
						row.setQuantidade(item.getQuantidade());
						produtos.add(row);
					}
				}
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
		return produtos;
	}
	
	public double getTotalPrice(ArrayList<Carrinho> carrinhoLista) {
		double soma = 0;
		try {
			if(carrinhoLista.size() > 0) {
				for(Carrinho item:carrinhoLista) {
					query = "select price from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					
					while(rs.next()) {
						soma += rs.getDouble("price")*item.getQuantidade();
					}
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return soma;
	}
}
