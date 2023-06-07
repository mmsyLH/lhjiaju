package xyz.lhweb.furns.service.impl;

import java.util.List;

import xyz.lhweb.furns.bean.Furn;
import xyz.lhweb.furns.bean.Page;
import xyz.lhweb.furns.dao.FurnDao;
import xyz.lhweb.furns.factory.DaoFactory;
import xyz.lhweb.furns.service.FurnService;

/**
 * 家具服务实现
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class FurnServiceImpl implements FurnService {
	private FurnDao furnDao = DaoFactory.getFurnDao();

	/**
	 * 查询家具
	 *
	 * @return {@link List}<{@link Furn}>
	 */
	@Override
	public List<Furn> queryFurns() {
		return furnDao.queryFurns();
	}

	/**
	 * 添加家具
	 *
	 * @param furn 家具
	 * @return int
	 */
	@Override
	public int addFurns(Furn furn) {
		return furnDao.addFurns(furn);
	}

	/**
	 * 删除家具通过id
	 *
	 * @param id id
	 * @return int 是受影响的行数
	 */
	@Override
	public int deleteFurnById(int id) {
		return furnDao.deleteFurnById(id);
	}

	/**
	 * 通过id查询家具
	 *
	 * @return {@link Furn}
	 */
	@Override
	public Furn queryFurnById(int id) {
		return furnDao.queryFurnById(id);
	}

	/**
	 * 更新家具 将传入的furn对象，更新到数据库，根据id id是唯一标识
	 *
	 * @param furn 家具
	 * @return int
	 */
	@Override
	public int updateFurn(Furn furn) {
		return furnDao.updateFurn(furn);
	}

	/**
	 * 根据传入的begin和pageSize返回传入对应page对象
	 *
	 * @param pageNo   开始
	 * @param pageSize 页面大小
	 * @return {@link Page}<{@link Furn}>
	 */
	@Override
	public Page<Furn> page(int pageNo, int pageSize) {
		// 先创建一个page对象，然后根据实际情况填充属性
		Page<Furn> page = new Page<>();
		/**
		 *
		 * ////因为每页显示多少条记录，是其它地方也可以使用 ////ctrl+shift+u => 切换大小写 //public static final
		 * Integer PAGE_SIZE = 3; // ////表示显示当前页[显示第几页] ////前端页面来的 //private Integer
		 * pageNo; ////表示每页显示几条记录 //private Integer pageSize = PAGE_SIZE; ////表示共有多少页,
		 * 他是计算得到 //private Integer pageTotalCount; ////表示的是共有多少条记录 ,
		 * 通过totalRow和pageSize ////计算得到pageTotalCount ////是可以同数据库DB来的->DAO //private
		 * Integer totalRow; ////表示当前页，要显示的数据 ////从DB来的->DAO //private List<T> items;
		 * ////分页导航的字符串 //private String url;
		 */
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		int totalRow = furnDao.getTotalRow();
		page.setTotalRow(totalRow);
		// 比如 6 2 =》 6 / 2 = 3
		// 比如 5 2 =》 5 / 2 = 2
		// 验证 7 3 =>
		// 验证 0 2 =>
		int pageTotalCount = totalRow / pageSize;
		if (totalRow % pageSize > 0) {
			pageTotalCount += 1;
		}
		page.setPageTotalCount(pageTotalCount);
		// private List<T> items;
		// 验证: pageNo = 1 pageSize = 3 => begin =0
		// 验证: pageNo = 3 pageSize = 2 => begin =4
		// OK => 但是注意这里隐藏一个坑, 现在你看不到, 后面会暴露
		int begin = (pageNo - 1) * pageSize;
		List<Furn> pageItems = furnDao.getPageItems(begin, pageSize);
		page.setItems(pageItems);
		return page;
	}

	/**
	 * 根据传入的pageNo,pageSize,name返回传入对应的page对象
	 *
	 * @param pageNo   页面没有
	 * @param pageSize 页面大小
	 * @param name     名字
	 * @return {@link Page}<{@link Furn}>
	 */
	@Override
	public Page<Furn> pageByName(int pageNo, int pageSize, String name) {
		// 先创建一个page对象，然后根据实际情况填充属性
		Page<Furn> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		int totalRow = furnDao.getTotalRowByName(name);
		page.setTotalRow(totalRow);
		// 比如 6 2 =》 6 / 2 = 3
		// 比如 5 2 =》 5 / 2 = 2
		int pageTotalCount = totalRow / pageSize;
		if (totalRow % pageSize > 0) {
			pageTotalCount += 1;
		}
		page.setPageTotalCount(pageTotalCount);
		// private List<T> items;
		// 验证: pageNo = 1 pageSize = 3 => begin =0
		// 验证: pageNo = 3 pageSize = 2 => begin =4
		// OK => 但是注意这里隐藏一个坑, 现在你看不到, 后面会暴露
		int begin = (pageNo - 1) * pageSize;
		List<Furn> pageItems = furnDao.getPageItemsByname(begin, pageSize, name);
		// 这里获取图片路绝对径将图片转换为base64
		// for (Furn fun:pageItems){
		// //遍历明细 获取明细中图片路径并转换为文件流
		// //直接将路径写死了 这是绝对路径 其实可以上网上找 获取服务器目录的代码
		// String strImg = fun.getImgPath();//图片路径 我写了一份的 没事算了 后期你再找 你自己可以改造
		// String StrSystemUrl =
		// "F:\\JavaWorksparce\\ecilpseWorkspace\\2203840110_luohan\\src\\main\\webapp\\";
		// //转base64的代码我就不写了 直接上网上找这种代码 没必要写 网上大把
		// //其实 图片可以压缩的 还可以用代码裁剪 具体可以网上搜索资料
		// try {
		// String strBase64 = encodeBase64File(StrSystemUrl+strImg );
		// fun.setImgPath(strBase64);
		// }catch (Exception exception){
		// //这里不能反回null 了 不然一张图片有问题会影响其他的数据
		// //直接跳出当前循环即可
		// continue;
		// }
		// //File file = new File(StrSystemUrl+strImg);这是根据绝对路径找到文件并转换成文件流
		// }
		page.setItems(pageItems);
		return page;
	}

	/**
	 * <p>
	 * 将文件转成base64 字符串
	 * </p>
	 * 
	 * @param path 文件路径
	 * @return
	 * @throws Exception
	 */
//	public static String encodeBase64File(String path) throws Exception {
//		File file = new File(path);
//		FileInputStream inputFile = new FileInputStream(file);
//		byte[] buffer = new byte[(int) file.length()];
//		inputFile.read(buffer);
//		inputFile.close();
//		return new BASE64Encoder().encode(buffer);
//	}

}
