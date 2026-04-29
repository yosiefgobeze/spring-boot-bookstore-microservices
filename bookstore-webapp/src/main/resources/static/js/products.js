document.addEventListener('alpine:init', () => {
    Alpine.data('initData', (pageNo) => ({
        pageNo: pageNo,
        products: {
            data: []
        },
        init() {
            this.loadProducts(this.pageNo);
        },
        loadProducts(pageNo) {
            // $.getJSON(apiGatewayUrl+"/catalog/api/products?page="+pageNo, (resp)=> {
            //     console.log("Products Resp:", resp)
            //     this.products = resp;
            // });
            // Below one uses the ProductController api call
            $.getJSON("/api/products?page="+pageNo, (resp)=> {
                console.log("Products Resp:", resp)
                this.products = resp;
            });
        },
        addToCart(product) {
            addProductToCart(product)
        }
    }))
});